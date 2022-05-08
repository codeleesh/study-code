package com.lovethefeel.webflux.user.repository;

import com.lovethefeel.webflux.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
