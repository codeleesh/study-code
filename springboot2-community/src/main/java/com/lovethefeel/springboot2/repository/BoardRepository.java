package com.lovethefeel.springboot2.repository;

import com.lovethefeel.springboot2.domain.Board;
import com.lovethefeel.springboot2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
