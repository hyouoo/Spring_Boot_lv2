package com.sparta.lv2.dto;


import com.sparta.lv2.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String gender;
    private String ssn;
    private String phone;
    private String address;
}
