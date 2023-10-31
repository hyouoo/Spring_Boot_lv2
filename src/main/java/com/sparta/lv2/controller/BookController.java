package com.sparta.lv2.controller;

import com.sparta.lv2.dto.BookRequestDto;
import com.sparta.lv2.dto.BookResponseDto;
import com.sparta.lv2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/register")
    public BookResponseDto registerBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.registerBook(bookRequestDto);
    }

    @GetMapping("")
    public List<BookResponseDto> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getEachBook(@PathVariable Long bookId) {
        return bookService.getEachBook(bookId);
    }
}
