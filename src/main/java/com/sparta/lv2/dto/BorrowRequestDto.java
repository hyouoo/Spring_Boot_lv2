package com.sparta.lv2.dto;

import lombok.Getter;

@Getter
public class BorrowRequestDto {
    private Long userId;
    private Long bookId;
    private Long borrowId;
}
