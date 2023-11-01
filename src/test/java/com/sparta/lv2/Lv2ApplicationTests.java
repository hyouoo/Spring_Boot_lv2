package com.sparta.lv2;

import com.sparta.lv2.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lv2ApplicationTests {

    @Autowired
    private BorrowService borrowService;

    @Test
    public void returnBook() {
        Long borrowId = 7L;
    }

}
