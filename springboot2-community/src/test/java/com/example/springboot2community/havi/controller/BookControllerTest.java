package com.example.springboot2community.havi.controller;

import com.example.springboot2community.havi.domain.Book;
import com.example.springboot2community.havi.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getBookList() throws Exception {

        // given
        Book book = new Book("Spring Boot Book", LocalDateTime.now());
        given(bookService.getBookList()).willReturn(Collections.singletonList(book));

        // when
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attributeExists("bookList"));
    }
}
