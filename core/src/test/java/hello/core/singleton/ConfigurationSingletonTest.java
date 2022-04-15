package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        final MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        final OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        final MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        final MemberRepository memberRepository1 = memberService.getMemberRepository();
        final MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository " + memberRepository1);
        System.out.println("orderService -> memberRepository " + memberRepository2);
        System.out.println("memberRepository " + memberRepository);

        assertAll(
                () -> assertThat(memberRepository1).isSameAs(memberRepository2),
                () -> assertThat(memberRepository1).isSameAs(memberRepository),
                () -> assertThat(memberRepository2).isSameAs(memberRepository)
        );
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
