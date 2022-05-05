package com.lovethefeel.webflux.repository;

import com.lovethefeel.webflux.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
