package com.lovethefeel.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovethefeel.springboot.domain.user.UserSequence;
import com.lovethefeel.springboot.dto.user.UserSequenceRequest;
import com.lovethefeel.springboot.repository.user.SpringDataJpaUserSequenceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SpringDataJpaUserSequenceRepository springDataJpaUserSequenceRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    public void testSave() throws Exception {

        // given
        UserSequenceRequest userSequenceRequest = UserSequenceRequest.builder()
                .name("테스트")
                .build();

        String url = "http://localhost:"+ port + "/api/v1/user";

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userSequenceRequest)))
                .andExpect(status().isOk());

        // then
        Optional<UserSequence> userSequence = springDataJpaUserSequenceRepository.findByName("테스트");
        Assertions.assertThat(userSequence).isNotEmpty();
    }
}
