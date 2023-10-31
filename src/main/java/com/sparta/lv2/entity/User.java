package com.sparta.lv2.entity;

import com.sparta.lv2.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private boolean borrowable;

    @Column(name = "penalty", nullable = false)
    private boolean penalty;

    @Column(name = "borrowDate")
    private LocalDateTime borrowDate;

    @Column(name = "returnDate")
    private LocalDateTime returnDate;

    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
        this.gender = userRequestDto.getGender();
        this.ssn = userRequestDto.getSsn();
        this.phone = userRequestDto.getPhone();
        this.address = userRequestDto.getAddress();
        this.borrowable = true;
        this.penalty = false;
    }

    public void setBorrowable(boolean flag) {
        this.borrowable = flag;
    }

    public void setPenalty(boolean flag) {
        this.penalty = flag;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
