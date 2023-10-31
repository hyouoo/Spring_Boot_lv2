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

    public UserRequestDto(String username, String gender, String ssn, String phone, String address) {
        if (username == null) throw new IllegalArgumentException("이름을 입력해 주세요.");
        if (gender == null) throw new IllegalArgumentException("성별을 입력해 주세요.");
        if (ssn==null) throw new IllegalArgumentException("주민번호를 입력해 주세요.");
        if (phone == null) throw new IllegalArgumentException("전화번호를 입력해 주세요.");

        this.username = username;
        this.gender = gender;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
    }
}
