package com.sparta.lv2.dto;

import com.sparta.lv2.entity.Borrow;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BorrowResponseDto {
    private String message;

    private final String username;
    private final String phone;
    private final String title;
    private final String author;
    private final boolean borrowing;
    private final LocalDate borrowDate;
    private final LocalDate returnDate;

    public BorrowResponseDto(Borrow borrow) {
        this.username = borrow.getUsername();
        this.phone = borrow.getPhone();
        this.title = borrow.getTitle();
        this.author = borrow.getAuthor();
        this.borrowing = borrow.getBorrowing();
        this.borrowDate = borrow.getBorrowDate();
        this.returnDate = borrow.getReturnDate();
    }

    public BorrowResponseDto(Borrow borrow, String message) {
        this.message = message;
        this.username = borrow.getUsername();
        this.phone = borrow.getPhone();
        this.title = borrow.getTitle();
        this.author = borrow.getAuthor();
        this.borrowing = borrow.getBorrowing();
        this.borrowDate = borrow.getBorrowDate();
        this.returnDate = borrow.getReturnDate();
    }
}
