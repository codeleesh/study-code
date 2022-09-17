package item6.domain;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class CheckSeparatorAndDelimiterBeforeTest {

    @Test
    void checkSeparatorAndDelimiter() {
        // given
        final String 콤마포함숫자 = ",123";

        // when
        Boolean result = false;
        final CheckSeparatorAndDelimiterBefore check = new CheckSeparatorAndDelimiterBefore();
        for (int i = 0; i < 1000000; i++) {
            if (result != null)
                result = check.checkSeparatorAndDelimiter(콤마포함숫자);
        }

        // then
        assertEquals(result, true);
    }
}