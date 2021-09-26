package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserIdentityRepository extends JpaRepository<UserIdentity, Long> {

    Optional<UserIdentity> findByName(String name);
}
