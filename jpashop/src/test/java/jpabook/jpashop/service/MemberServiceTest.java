package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @DisplayName("회원가입을 하면 회원 정보가 저장됩니다.")
    @Test
    public void 회원가입() {
        //  given
        final Member member = Member.from("kim");

        // when
        final Long savedId = memberService.join(member);
        final Member findMember = memberRepository.findOne(savedId);

        // then
        assertEquals(member, findMember);
    }

    @DisplayName("이미 등록된 회원이 존재할 경우 예외처리를 합니다.")
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        // given
        final Member member1 = Member.from("kim");
        final Member member2 = Member.from("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("이것이 실행이 되면 안됩니다. 예외가 발생해야 합니다.");
    }
}