package com.lovethefeel.springboot2.havi.repository;

import com.lovethefeel.springboot2.havi.domain.Book;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void Book_저장하기_테스트() {

        // given
        Book book = Book.builder().title(BOOT_TEST_TITLE)
                .publishedAt(LocalDateTime.now()).build();

        // when
        testEntityManager.persist(book);

        // then
        assertThat(book).isEqualTo(bookRepository.getById(book.getId()));
    }

    @Test
    public void Book_저장하고_검색_테스트() {

        // given
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1")
                .publishedAt(LocalDateTime.now()).build();
        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2")
                .publishedAt(LocalDateTime.now()).build();
        Book book3 = Book.builder().title(BOOT_TEST_TITLE+"3")
                .publishedAt(LocalDateTime.now()).build();

        // when
        testEntityManager.persist(book1);
        testEntityManager.persist(book2);
        testEntityManager.persist(book3);

        // then
        List<Book> bookList = bookRepository.findAll();
        assertThat(3).isEqualTo(bookList.size());
        assertThat(bookList).contains(book1,book2,book3);

    }

    @Test
    public void BookList_저장하고_삭제_테스트() {

        // given
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1")
                .publishedAt(LocalDateTime.now()).build();
        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2")
                .publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);
        testEntityManager.persist(book2);

        // when
        bookRepository.deleteAll();

        // then
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).isEmpty();
        assertThat(IsEmptyCollection.empty()).isNotEqualTo(bookList);
    }
}
