package me.lovethefeel.jpahistory.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.lovethefeel.jpahistory.member.domain.Member;

import java.util.List;

import static me.lovethefeel.jpahistory.member.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findByName(final String createBy) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.memberName.eq(createBy))
                .fetch();
    }
}
