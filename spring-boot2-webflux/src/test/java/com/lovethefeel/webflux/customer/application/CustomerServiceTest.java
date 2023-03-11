package com.lovethefeel.webflux.customer.application;

import com.lovethefeel.webflux.customer.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@DisplayName("고객 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void 메시지전송이_실패한다() {

        final Customer customer = new Customer();

        doThrow(new AmqpException(""))
                .when(rabbitTemplate).convertAndSend(any());

        assertThatThrownBy(() -> customerService.sendMessage(customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("메세지전송 호출에 실패하였습니다.");
    }
}