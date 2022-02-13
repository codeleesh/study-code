package com.lovethefeel.constructor.dto;

import com.lovethefeel.constructor.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class BookResponse {
    private Long id;
    private String name;

    public static BookResponse of(final Long id, final String name) {
        return new BookResponse(id, name);
    }

    public static BookResponse from(final Book book) {
        return new BookResponse(book.getId(), book.getName());
    }
}
