package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserSequence;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserSequence save(UserSequence userBigSerial);
    Optional<UserSequence> findById(Long id);
    Optional<UserSequence> findByName(String name);
    List<UserSequence> findAll();
}

