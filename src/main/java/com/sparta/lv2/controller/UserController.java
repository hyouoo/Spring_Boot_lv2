package com.sparta.lv2.controller;

import com.sparta.lv2.dto.BorrowRequestDto;
import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.dto.UserRequestDto;
import com.sparta.lv2.dto.UserResponseDto;
import com.sparta.lv2.service.BorrowService;
import com.sparta.lv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final BorrowService borrowService;

    @PostMapping("/register")
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerUser(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    @PatchMapping("/borrow")
    public BorrowResponseDto borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        return userService.borrowBook(borrowRequestDto);
    }

    @PatchMapping("/return")
    public BorrowResponseDto returnBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        return userService.returnBook(borrowRequestDto);
    }
}
