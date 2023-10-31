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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final UserService userService;

    private final BookService bookService;

    public List<BorrowResponseDto> getBorrows() {
        return borrowRepository.findAllByBorrowingIsTrueOrderByBorrowDateAsc().stream()
                .map(BorrowResponseDto::new).toList();
    }

    public BorrowResponseDto borrowBook(BorrowRequestDto borrowRequestDto) {
        User user = userService.findUser(borrowRequestDto.getUserId());
        Book book = bookService.findBook(borrowRequestDto.getBookId());

        if (borrowable(user, book)) {
            user.setBorrowable(false);
            user.setBorrowDate(LocalDateTime.now());
            book.setBorrowable(false);
        }
        userRepository.save(user);
        bookRepository.save(book);

        Borrow borrow = new Borrow(user, book);
        borrowRepository.save(borrow);

        String message = String.format("%s님의 %s 대출 요청이 처리되었습니다.", borrow.getUsername(), borrow.getTitle());

        return new BorrowResponseDto(borrow, message);
    }

    public BorrowResponseDto returnBook(Long borrowId) {
        Borrow borrow = findBorrow(borrowId);
        User user = userService.findUser(borrow.getUserId());
        Book book = bookService.findBook(borrow.getBookId());

        LocalDateTime current = LocalDateTime.now();
        Duration duration = Duration.between(current, user.getBorrowDate());
        if (duration.toDays() > 7) {
            user.setPenalty(true);
        } else {
            user.setBorrowable(true);
        }
        user.setReturnDate(LocalDateTime.now());
        book.setBorrowable(true);

        userRepository.save(user);
        bookRepository.save(book);

        borrow.setBorrowing(false);
        borrow.setReturnDate(LocalDateTime.now());
        borrowRepository.save(borrow);

        String message = String.format("%s님의 반납이 처리되었습니다.", borrow.getUsername());
        return new BorrowResponseDto(borrow, message);
    }

    private Borrow findBorrow(Long borrowId) {
        return borrowRepository.findById(borrowId).orElseThrow(() ->
                new IllegalArgumentException("대출 내역이 없습니다."));
    }

    private boolean borrowable(User user, Book book) {
        if (user.getReturnDate() != null && user.isPenalty()) {
            LocalDateTime current = LocalDateTime.now();
            Duration duration = Duration.between(current, user.getReturnDate());
            if (duration.toDays() < 14) {
                throw new IllegalArgumentException("페널티 기간 중이라 대출이 제한됩니다.");
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