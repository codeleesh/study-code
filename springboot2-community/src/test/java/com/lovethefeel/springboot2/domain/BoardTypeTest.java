package com.lovethefeel.springboot2.domain;

import com.lovethefeel.springboot2.domain.enums.BoardType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTypeTest {

    @Test
    public void testGetValue() {
        assertThat("자유게시판").isEqualTo(BoardType.free.getValue());
        assertThat("notice").isEqualTo(BoardType.notice.name());
    }

}
