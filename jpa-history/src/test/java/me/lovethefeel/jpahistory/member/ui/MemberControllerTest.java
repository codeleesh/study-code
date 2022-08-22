package me.lovethefeel.jpahistory.member.ui;

import me.lovethefeel.jpahistory.member.domain.Member;
import me.lovethefeel.jpahistory.member.dto.MemberRequest;
import me.lovethefeel.jpahistory.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MemberRepository memberRepository;

    private Member test;

    @BeforeAll
    void setUp() {

        test = Member.fromCreate("test");
        Member save = memberRepository.save(test);
    }

    @AfterAll
    void tearDown() {

        memberRepository.delete(test);
    }

    @Test
    void saveMember() {

        MemberRequest lsh = MemberRequest.from("lsh");

        webTestClient
                .post()
                .uri("/v1/member")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(lsh)
                .exchange()
                .expectBody()
                .returnResult();
    }
}
