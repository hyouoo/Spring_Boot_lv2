package com.sparta.lv2.controller;

import com.sparta.lv2.dto.BorrowRequestDto;
import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping("")
    public List<BorrowResponseDto> getBorrows() {
        return borrowService.getBorrows();
    }

    @GetMapping("/ing")
    public List<BorrowResponseDto> getBorrowsIng() {
        return borrowService.getBorrowsIng();
    }

    @GetMapping("/{userId}")
    public List<BorrowResponseDto> getUserBorrows(@PathVariable Long userId) {
        return borrowService.getUserBorrows(userId);
    }

    @GetMapping("/ing/{userId}")
    public List<BorrowResponseDto> getUserBorrowsIng(@PathVariable Long userId) {
        return borrowService.getUserBorrowsIng(userId);
    }

    @PutMapping("")
    public BorrowResponseDto borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        return borrowService.borrowBook(borrowRequestDto);
    }

    @PatchMapping("/{borrowId}")
    public BorrowResponseDto returnBook(@PathVariable Long borrowId) {
        return borrowService.returnBook(borrowId);
    }
}
