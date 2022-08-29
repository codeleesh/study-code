package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.config.TestConfig;
import me.lovethefeel.jpahistory.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository historyRepository;

    @Test
    void 성공_멤버_저장() {

        final Member member = Member.fromCreate("lsh");

        final Member saveMember = memberRepository.save(member);

        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    void 성공_조회_querydsl_custom_기능확인() {

        final String memberName = "lsh";
        final Member member = Member.fromCreate(memberName);
        memberRepository.save(member);

        final List<Member> members = memberRepository.findByName(memberName);

        assertThat(members).hasSize(1);
    }
}