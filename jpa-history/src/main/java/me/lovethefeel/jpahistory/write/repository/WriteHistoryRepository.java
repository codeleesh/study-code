package me.lovethefeel.jpahistory.write.repository;

import me.lovethefeel.jpahistory.write.domain.WriteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteHistoryRepository extends JpaRepository<WriteHistory, Long> {
}
