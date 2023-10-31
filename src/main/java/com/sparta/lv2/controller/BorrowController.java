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

    @PutMapping("")
    public BorrowResponseDto borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        return borrowService.borrowBook(borrowRequestDto);
    }

    @PatchMapping("/{borrowId}")
    public BorrowResponseDto returnBook(@PathVariable Long borrowId) {
        return borrowService.returnBook(borrowId);
    }
}
