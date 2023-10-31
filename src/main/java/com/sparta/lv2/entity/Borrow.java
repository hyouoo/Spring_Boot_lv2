package com.sparta.lv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime borrowDate;

    @Column(name = "returnDate")
    private LocalDateTime returnDate;

    public Borrow(User user, Book book) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.borrowing = true;
        this.borrowDate = LocalDateTime.now();
    }

    public void setBorrowing(Boolean flag) {
        this.borrowing = flag;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
