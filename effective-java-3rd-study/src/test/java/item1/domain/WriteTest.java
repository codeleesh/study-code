package item1.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriteTest {

    @DisplayName("글 초기 생성을 정적 팩토리 메소드를 이용한다.")
    @Test
    void 글_생성() {
        final Write write = Write.initCreate(1L, "title", "content", "lee");

        assertAll(
                () -> assertEquals(write.getId(), 1L),
                () -> assertEquals(write.getTitle(), "title"),
                () -> assertEquals(write.getContent(), "content"),
                () -> assertEquals(write.getCreatedBy(), "lee"),
                () -> assertEquals(write.getUpdateBy(), "lee")
        );
    }
}