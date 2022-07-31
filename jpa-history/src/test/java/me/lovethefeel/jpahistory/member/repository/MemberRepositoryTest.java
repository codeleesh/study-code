package me.lovethefeel.jpahistory.member.repository;

import me.lovethefeel.jpahistory.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository historyRepository;

    @Test
    void create_member_and_history() {

        final Member member = Member.fromCreate("lsh");

        final Member saveMember = memberRepository.save(member);

        assertThat(saveMember).isEqualTo(member);
    }
}