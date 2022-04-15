package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        final AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @DisplayName("회원의 정보를 저장할 수 있습니다.")
    @Test
    void join() {
        // given
        Member member = new Member(1L, "name", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
