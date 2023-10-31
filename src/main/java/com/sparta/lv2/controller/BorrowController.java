package com.sparta.lv2.controller;

import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping("/api/borrow")
    public BorrowResponseDto getBorrows() {
        return borrowService.getBorrows();
    }
}
