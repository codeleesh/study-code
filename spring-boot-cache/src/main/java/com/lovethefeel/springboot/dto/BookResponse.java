package com.lovethefeel.springboot.dto;

import com.lovethefeel.springboot.domain.Book;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String name;

    public static BookResponse from(final Book book) {
        return new BookResponse(book.getId(), book.getName());
    }

    public static List<BookResponse> from(final List<Book> books) {
        return books.stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }
}
