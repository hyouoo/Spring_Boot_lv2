package com.sparta.lv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "borrowList")
public class Borrow extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;

    @JoinColumn(name = "userId", table = "users", referencedColumnName = "userId")
    private Long userId;

    @JoinColumn(name = "bookId", table = "books", referencedColumnName = "bookId")
    private Long bookId;
}
