package com.sparta.lv2.entity;

import com.sparta.lv2.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "ssn", nullable = false, unique = true)
    private String ssn;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "borrowable", nullable = false)
    private boolean borrowable = true;

    @JoinColumn(name = "bookId", table = "books", referencedColumnName = "bookId")
    private Long borrowBookId = null;

    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
        this.gender = userRequestDto.getGender();
        this.ssn = userRequestDto.getSsn();
        this.phone = userRequestDto.getPhone();
        this.address = userRequestDto.getAddress();
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public void setBorrowBookId(Long bookId) {
        this.borrowBookId = bookId;
    }
}