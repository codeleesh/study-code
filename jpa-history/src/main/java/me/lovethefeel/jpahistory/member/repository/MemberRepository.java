package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
