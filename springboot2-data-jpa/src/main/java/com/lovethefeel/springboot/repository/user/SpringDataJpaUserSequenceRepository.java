package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.config.aop.LogExecutionTime;
import com.lovethefeel.springboot.domain.user.UserSequence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserSequenceRepository extends JpaRepository<UserSequence, Long> {

    @LogExecutionTime
    Optional<UserSequence> findByName(String name);
}
