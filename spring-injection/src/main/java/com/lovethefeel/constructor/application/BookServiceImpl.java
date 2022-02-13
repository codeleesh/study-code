package com.lovethefeel.constructor.application;

import com.lovethefeel.constructor.Book;
import com.lovethefeel.constructor.BookRepository;
import com.lovethefeel.constructor.dto.BookRequest;
import com.lovethefeel.constructor.dto.BookResponse;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse saveBook(final BookRequest bookRequest) {
        final Book saveBook = bookRepository.save(bookRequest.toEntity());
        return BookResponse.from(saveBook);
    }
}
