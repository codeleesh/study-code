package me.lovethefeel.jpahistory.write.repository;

import me.lovethefeel.jpahistory.write.domain.Write;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteRepository extends JpaRepository<Write, Long> {
}
