package com.sparta.lv2.dto;

import com.sparta.lv2.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String gender;
    private String phone;
    private String address;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }
}
