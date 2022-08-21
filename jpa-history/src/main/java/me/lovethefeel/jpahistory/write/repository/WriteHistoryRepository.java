package me.lovethefeel.jpahistory.write.repository;

import me.lovethefeel.jpahistory.write.domain.WriteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WriteHistoryRepository extends JpaRepository<WriteHistory, Long> {

    List<WriteHistory> findByWriteId(final Long writeId);
}
