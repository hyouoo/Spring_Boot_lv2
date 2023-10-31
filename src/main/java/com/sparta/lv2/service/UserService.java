package com.sparta.lv2.service;

import com.sparta.lv2.dto.BorrowRequestDto;
import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.dto.UserRequestDto;
import com.sparta.lv2.dto.UserResponseDto;
import com.sparta.lv2.entity.Book;
import com.sparta.lv2.entity.User;
import com.sparta.lv2.exception.ResourceNotFoundException;
import com.sparta.lv2.repository.BorrowRepository;
import com.sparta.lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final BorrowRepository borrowRepository;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        User newUser = userRepository.save(user);
        return new UserResponseDto(newUser);
    }

    public UserResponseDto getUserInfo(Long userId) {
        User user = findUser(userId);
        return new UserResponseDto(user);
    }

    public BorrowResponseDto borrowBook(BorrowRequestDto borrowRequestDto) {
        User user = findUser(borrowRequestDto.getUserId());
        Book book = bookService.findBook(borrowRequestDto.getBookId());
        if (borrowable(user, book)) {
            user.setBorrowable(false);
            user.setBorrowBookId(book.getBookId());
            book.setBorrowable(false);
        }
        return new BorrowResponseDto(user, book);
    }

    public BorrowResponseDto returnBook(BorrowRequestDto borrowRequestDto) {
        User user = findUser(borrowRequestDto.getUserId());
        Book book = bookService.findBook(borrowRequestDto.getBookId());
        user.setBorrowable(true);
        user.setBorrowBookId(null);
        book.setBorrowable(true);
        return new BorrowResponseDto(user);
    }

    private boolean borrowable(User user, Book book) {
        if (user.isBorrowable()) {
            if (book.isBorrowable()) {
                return true;
            } else {
                throw new ResourceNotFoundException("선택한 책은 대출 중입니다.");
            }
        }
        throw new IllegalArgumentException("대출 중인 책을 먼저 반납하세요.");
    }


    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("회원 정보가 없습니다."));
    }
}
