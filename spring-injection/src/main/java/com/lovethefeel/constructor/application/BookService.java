package com.lovethefeel.constructor.application;

import com.lovethefeel.constructor.dto.BookRequest;
import com.lovethefeel.constructor.dto.BookResponse;

public interface BookService {
    BookResponse saveBook(final BookRequest bookRequest);
}
