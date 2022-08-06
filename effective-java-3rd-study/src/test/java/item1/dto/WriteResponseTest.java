package item1.dto;

import item1.domain.Write;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriteResponseTest {

    @Test
    void 글작성_응답() {

        // given
        final Write write = Write.initCreate(1L, "title", "content", "lsh");

        // when
        final WriteResponse response = WriteResponse.from(write);

        // then
        assertAll(
                () -> assertEquals(response.getId(), write.getId()),
                () -> assertEquals(response.getTitle(), write.getTitle()),
                () -> assertEquals(response.getContent(), write.getContent()),
                () -> assertEquals(response.getCreatedBy(), write.getCreatedBy())
        );
    }
}