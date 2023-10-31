package com.sparta.lv2.service;

import com.sparta.lv2.dto.BorrowResponseDto;
import com.sparta.lv2.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;


    public List<BorrowResponseDto> getBorrows() {
        return borrowRepository.findAllByOrderByBorrowDate().stream()
                .map(BorrowResponseDto::new).toList();
    }
}
