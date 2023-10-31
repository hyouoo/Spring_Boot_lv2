package com.sparta.lv2.entity;

import com.sparta.lv2.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "language")
    private String language;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "borrowable", nullable = false)
    private boolean borrowable;

    @Column(name = "registerAt", updatable = false)
    private LocalDateTime registerAt;

    public Book(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.language = requestDto.getLanguage();
        this.publisher = requestDto.getPublisher();
        this.borrowable = true;
        this.registerAt = LocalDateTime.now();
    }

    public void setBorrowable(boolean flag) {
        this.borrowable = flag;
    }
}
