package com.lovethefeel.springboot2.havi.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void json_테스트() throws IOException {

        // given
        Book book = Book.builder()
                .title("테스트")
                .build();
        String content = "{\"title\":\"테스트\"}";

        // 변환된 객체의 title이 일치하는지 테스트
        assertThat(book.getTitle()).isEqualTo(this.json.parseObject(content).getTitle());

        // publishedAt 값을 정의하지 않았기 때문에 null인지 테스트
        assertThat(this.json.parseObject(content).getPublishedAt()).isNull();

        // 각 필드를 번환한 문자열이 test.json 파일에 정의한 내용과 일치하는지 테스트
        assertThat(this.json.write(book)).isEqualToJson("/test.json");

        // title 값이 있는지 테스트
        assertThat(this.json.write(book)).hasJsonPathStringValue("title");

        // title 값이 일치하는지 테스트
        assertThat(this.json.write(book)).extractingJsonPathStringValue("title")
                .isEqualTo("테스트");
    }

}
