package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.member.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByName(final String createBy);
}
