package com.sparta.lv2.dto;

import com.sparta.lv2.entity.Book;
import com.sparta.lv2.entity.Borrow;
import com.sparta.lv2.entity.User;
import lombok.Getter;

@Getter
public class BorrowResponseDto {
    private String message;

    public BorrowResponseDto(User user, Book book) {
        this.message = String.format("%s님의 %s 대출 요청이 처리되었습니다.", user.getUsername(), book.getTitle());
    }

    public BorrowResponseDto(User user) {

        this.message = String.format("%s님의 반납이 처리되었습니다.", user.getUsername());
    }

    public BorrowResponseDto(Borrow borrow) {
        this.username = borrow.getUsername;
    }
}
