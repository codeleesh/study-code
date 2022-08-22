package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    void deleteAllByMemberName(final String memberName);
}
