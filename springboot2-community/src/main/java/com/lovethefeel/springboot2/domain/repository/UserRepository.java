package com.lovethefeel.springboot2.domain.repository;

import com.lovethefeel.springboot2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
