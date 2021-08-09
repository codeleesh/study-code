package com.lovethefeel.springboot2.havi.repository;

import com.lovethefeel.springboot2.havi.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
