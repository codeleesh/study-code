package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.member.domain.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}
