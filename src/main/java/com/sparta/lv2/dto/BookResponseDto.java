package com.sparta.lv2.dto;

import com.sparta.lv2.entity.Book;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {
    private final Long bookId;
    private final String title;
    private final String author;
    private final String language;
    private final String publisher;
    private final boolean borrowable;
    private final LocalDateTime registerAt;

    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.borrowable = book.isBorrowable();
        this.registerAt = book.getRegisterAt();
    }
}