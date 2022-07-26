package me.lovethefeel.member.application;

import me.lovethefeel.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

    @Rule
    public MockitoRule mockitorule = MockitoJUnit.rule();

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void testSave() {

        final String response = "error";
        final Member requestMember = Member.from("lsh");

        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenReturn( new ResponseEntity<>(response, HttpStatus.OK));

        assertThatThrownBy(() -> memberService.save(requestMember))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("custom error");
    }
}