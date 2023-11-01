package com.sparta.lv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "borrowList")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "bookId", nullable = false)
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "status", nullable = false)
    private Boolean borrowing;

    @Column(name = "borrowDate")
    private LocalDate borrowDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    public Borrow(User user, Book book) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.borrowing = true;
        this.borrowDate = LocalDate.now();
    }

    public void setBorrowing(Boolean flag) {
        this.borrowing = flag;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
