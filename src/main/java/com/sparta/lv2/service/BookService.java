package com.sparta.lv2.service;

import com.sparta.lv2.dto.BookRequestDto;
import com.sparta.lv2.dto.BookResponseDto;
import com.sparta.lv2.entity.Book;
import com.sparta.lv2.exception.ResourceNotFoundException;
import com.sparta.lv2.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDto registerBook(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto);
        Book savedBook = bookRepository.save(book);
        return new BookResponseDto(savedBook);
    }

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAllByOrderByRegisterAtAsc().stream()
                .map(BookResponseDto::new).toList();
    }

    public BookResponseDto getEachBook(Long bookId) {
        Book book = findBook(bookId);
        return new BookResponseDto(book);
    }

    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new ResourceNotFoundException("해당 책이 존재하지 않습니다."));
    }
}
