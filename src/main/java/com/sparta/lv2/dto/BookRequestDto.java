package com.sparta.lv2.dto;

import lombok.Getter;

@Getter
public class BookRequestDto {
    private String title;
    private String author;
    private String language;
    private String publisher;

    public BookRequestDto(String title, String author, String language, String publisher) {
        if (title == null) throw new IllegalArgumentException("책 제목을 입력해 주세요.");
        if (author == null) throw new IllegalArgumentException("책의 저자를 입력해 주세요");

        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
    }
}
