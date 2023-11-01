package com.sparta.lv2.service;

import com.sparta.lv2.dto.BorrowRequestDto;
import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.entity.Book;
import com.sparta.lv2.entity.Borrow;
import com.sparta.lv2.entity.User;
import com.sparta.lv2.exception.ResourceNotFoundException;
import com.sparta.lv2.repository.BookRepository;
import com.sparta.lv2.repository.BorrowRepository;
import com.sparta.lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<BorrowResponseDto> getBorrows() {
        return borrowRepository.findAllByOrderByBorrowDateAsc().stream()
                .map(BorrowResponseDto::new).toList();
    }

    public List<BorrowResponseDto> getBorrowsIng() {
        return borrowRepository.findAllByBorrowingIsTrueOrderByBorrowDateAsc().stream()
                .map(BorrowResponseDto::new).toList();
    }

    public List<BorrowResponseDto> getUserBorrows(Long userId) {
        return borrowRepository.findAllByUserIdOrderByBorrowDateAsc(userId).stream()
                .map(BorrowResponseDto::new).toList();
    }

    public List<BorrowResponseDto> getUserBorrowsIng(Long userId) {
        return borrowRepository.findAllByBorrowingIsTrueAndUserIdOrderByBorrowDateAsc(userId).stream()
                .map(BorrowResponseDto::new).toList();
    }

    public BorrowResponseDto borrowBook(BorrowRequestDto borrowRequestDto) {
        User user = userRepository.findById(borrowRequestDto.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));
        Book book = bookRepository.findById(borrowRequestDto.getBookId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 책입니다."));

        if (borrowable(user, book)) {
            user.setBorrowable(false);
            user.setBorrowDate(LocalDate.now());
            book.setBorrowable(false);
        }
        User savedUser = userRepository.save(user);
        Book savedBook = bookRepository.save(book);

        Borrow borrow = new Borrow(savedUser, savedBook);
        Borrow savedBorrow = borrowRepository.save(borrow);

        String message = String.format("%s님의 %s 대출 요청이 처리되었습니다.", savedBorrow.getUsername(), savedBorrow.getTitle());

        return new BorrowResponseDto(savedBorrow, message);
    }

    public BorrowResponseDto returnBook(Long borrowId) {
        Borrow borrow = findBorrow(borrowId);
        User user = userRepository.findById(borrow.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다."));
        Book book = bookRepository.findById(borrow.getBookId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 책입니다."));

        LocalDate current = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(borrow.getBorrowDate(), current);
        if (daysBetween > 7) {
            user.setPenalty(true);
        } else {
            user.setBorrowable(true);
        }
        user.setReturnDate(LocalDate.now());
        book.setBorrowable(true);

        userRepository.save(user);
        bookRepository.save(book);

        borrow.setBorrowing(false);
        borrow.setReturnDate(LocalDate.now());
        Borrow savedBorrow = borrowRepository.save(borrow);

        String message;
        if (user.isPenalty()) {
            message = String.format("%s님의 반납이 처리되었으나, 반납 기한이 초과되어 앞으로 2주간 대출이 불가능합니다.", savedBorrow.getUsername());
        } else {
            message = String.format("%s님의 반납이 처리되었습니다.", savedBorrow.getUsername());
        }
        return new BorrowResponseDto(savedBorrow, message);
    }

    private Borrow findBorrow(Long borrowId) {
        return borrowRepository.findById(borrowId).orElseThrow(() ->
                new IllegalArgumentException("대출 내역이 없습니다."));
    }

    private boolean borrowable(User user, Book book) {
        if (user.isPenalty()) {
            LocalDate current = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(user.getReturnDate(), current);
            if (daysBetween < 14) {
                throw new IllegalArgumentException(String.format("페널티 기간 중이라 대출이 제한됩니다. %d일 후에 다시 오세요.", 14 - daysBetween));
            } else {
                user.setPenalty(false);
                user.setBorrowable(true);
            }
        }
        if (user.isBorrowable()) {
            if (book.isBorrowable()) {
                return true;
            } else {
                throw new ResourceNotFoundException("선택한 책은 대출 중입니다.");
            }
        }
        throw new IllegalArgumentException("대출 중인 책을 먼저 반납하세요.");
    }
}
