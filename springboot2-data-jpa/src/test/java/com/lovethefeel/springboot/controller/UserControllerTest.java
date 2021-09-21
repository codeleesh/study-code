package com.lovethefeel.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovethefeel.springboot.dto.user.UserSequenceRequest;
import com.lovethefeel.springboot.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    void testSave() throws Exception {

//        // given
//        UserSequenceRequest userSequenceRequest = UserSequenceRequest.builder()
//                .name("테스트")
//                .build();
//
//        String url = "http://localhost:"+ port + "/api/v1/user";
//
//        // when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(userSequenceRequest)))
//                .andExpect(status().isOk());

        // given
        UserSequenceRequest userSequenceRequest = UserSequenceRequest.builder()
                .name("테스트")
                .build();

        // when-then
        mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userSequenceRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))

        //when
        final ResultActions actions = mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userSequenceRequest)))
                .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("price").value(1000D))
        ;

    }
}
