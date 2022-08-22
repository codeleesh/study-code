package me.lovethefeel.jpahistory.write.repository;

import me.lovethefeel.jpahistory.write.domain.Write;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WriteRepository extends JpaRepository<Write, Long> {

    @Transactional
    void deleteAllByWriteName(final String name);
}
