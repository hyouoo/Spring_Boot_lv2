package com.sparta.lv2.service;

import com.sparta.lv2.dto.UserRequestDto;
import com.sparta.lv2.dto.UserResponseDto;
import com.sparta.lv2.entity.User;
import com.sparta.lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        // 주민번호, 전화번호 check
        if (userRepository.existsBySsn(user.getSsn())) throw new IllegalArgumentException("중복된 주민번호입니다.");
        if (userRepository.existsByPhone(user.getPhone())) throw new IllegalArgumentException("중복된 전화번호입니다.");

        User newUser = userRepository.save(user);
        return new UserResponseDto(newUser);
    }

    public UserResponseDto getUserInfo(Long userId) {
        User user = findUser(userId);
        return new UserResponseDto(user);
    }

    User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("회원 정보가 없습니다."));
    }
}
