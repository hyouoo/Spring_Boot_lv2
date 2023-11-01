package com.sparta.lv2.dto;

import com.sparta.lv2.entity.Borrow;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BorrowResponseDto {
    private String message;

    private String username;
    private String phone;
    private String title;
    private String author;
    private boolean borrowing;
    private LocalDate borrowDate;
    private LocalDate returnDate;

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
