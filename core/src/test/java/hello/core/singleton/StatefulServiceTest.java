package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @DisplayName("상태를 나타내는 인스턴스 변수의 문제점")
    @Test
    void statefulServiceSingleton() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        final StatefulService statefulService1 = ac.getBean(StatefulService.class);
        final StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : A사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}