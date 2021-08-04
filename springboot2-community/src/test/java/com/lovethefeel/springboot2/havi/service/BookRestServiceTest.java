package com.lovethefeel.springboot2.havi.service;

import com.lovethefeel.springboot2.havi.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BookRestService.class)
public class BookRestServiceTest {

    @Autowired
    private BookRestService bookRestService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    public void rest_테스트() {

        // given
        this.mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(withSuccess(new ClassPathResource("/test.json",
                        getClass()), MediaType.APPLICATION_JSON));

        // when
        Book book = this.bookRestService.getRestBook();

        // then
        assertThat("테스트").isEqualTo(book.getTitle());
    }

    @Test
    public void rest_error_테스트() {

        // given
        this.mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(withServerError());

        // when
        // then
        Assertions.assertThrows(HttpServerErrorException.class,
                () -> this.bookRestService.getRestBook()
        );

    }
}
