package com.lovethefeel.http.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIdAndName(final Long id, final String name);

    Book findByName(final String name);
}
