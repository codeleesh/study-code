package com.lovethefeel.springboot.application;

import com.lovethefeel.springboot.domain.Book;
import com.lovethefeel.springboot.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMemoryService {

    private final BookRepository bookRepository;

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.books = loadBooks();
    }

    private List<Book> loadBooks() {
        final List<Book> result = bookRepository.findAll();
        log.info("BookMemoryService books [{}]", result);
        return result;
    }

    public List<Book> getBooks() {
        return this.books;
    }
}
