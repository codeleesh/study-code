package com.lovethefeel.springboot2.domain.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTypeTest {

    @Test
    public void testGetValue() {
        assertThat(BoardType.free.getValue()).isEqualTo("자유게시판");
        assertThat(BoardType.notice.name()).isEqualTo("notice");
    }

}
