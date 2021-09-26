package com.lovethefeel.springboot.repository.usersequence;

import com.lovethefeel.springboot.config.aop.LogExecutionTime;
import com.lovethefeel.springboot.domain.user.UserSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSequenceRepository extends JpaRepository<UserSequence, Long> {

    @LogExecutionTime
    Optional<UserSequence> findByName(String name);

    @Query("SELECT COUNT(u.id) > 0 " +
            "FROM TN_USER_SEQUENCE u " +
            "WHERE o.sexs =:sexs")
    boolean exists(@Param("sexs") String sexs);
}
