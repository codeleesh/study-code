package com.lovethefeel.springboot.dto;

import com.lovethefeel.springboot.domain.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookRequest {

    private String name;

    public static BookRequest from(final String name) {
        return new BookRequest(name);
    }

    public Book toBook() {
        return Book.from(name);
    }
}
