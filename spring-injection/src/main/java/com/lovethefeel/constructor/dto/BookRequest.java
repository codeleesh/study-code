package com.lovethefeel.constructor.dto;

import com.lovethefeel.constructor.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class BookRequest {
    private String name;

    public static BookRequest from(final String name) {
        return new BookRequest(name);
    }

    public Book toEntity() {
        return Book.from(name);
    }
}
