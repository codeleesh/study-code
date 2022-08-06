package item1.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionsTest {
    
    @Test
    void collectionsCreateTest() {
        final String element = "list";
        List<String> stringList = Collections.singletonList(element);
        assertEquals(stringList.size(), 1);
    }
}
