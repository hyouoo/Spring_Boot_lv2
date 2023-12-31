package com.sparta.lv2.controller;

import com.sparta.lv2.dto.UserRequestDto;
import com.sparta.lv2.dto.UserResponseDto;
import com.sparta.lv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerUser(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }
}
