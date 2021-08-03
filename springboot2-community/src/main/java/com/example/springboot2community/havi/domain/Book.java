package com.example.springboot2community.havi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Book {

    private Integer idx;
    private String title;
    private LocalDateTime publishedAt;

    @Builder
    public Book(String title, LocalDateTime publishedAt) {
        this.title = title;
        this.publishedAt = publishedAt;
    }
}
