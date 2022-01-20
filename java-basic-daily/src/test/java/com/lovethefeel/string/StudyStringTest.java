package com.lovethefeel.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyStringTest {

    @DisplayName("String 인스턴스 변수의 비교는 동일하지 않습니다.")
    @Test
    void testSameness() {
        final String str1 = new String();
        final String str2 = new String();

        assertNotSame(str1, str2);
    }

    @DisplayName("String 인스턴스 변수의 값은 동등합니다.")
    @Test
    void testEquals() {
        final String str1 = new String();
        final String str2 = new String();

        assertEquals(str1, str2);
    }

    @DisplayName("String 인스턴스 변수의 값이 같다면 hashCode의 값은 같습니다.")
    @Test
    void testHashCodeValue() {
        final String str1 = new String(new char[]{'s', 't', 'u', 'd', 'y'});
        final String str2 = new String("study");
        final String str3 = "study";

        assertAll(
                () -> assertEquals(str1.hashCode(), str2.hashCode()),
                () -> assertEquals(str2.hashCode(), str3.hashCode()),
                () -> assertEquals(str1.hashCode(), str3.hashCode())
        );
    }

    @DisplayName("String 인스턴스 변수의 값이 같다면 hashCode의 값은 같지만 동일하지는 않습니다.")
    @Test
    void testHashCode() {
        final String str1 = new String(new char[]{'s', 't', 'u', 'd', 'y'});
        final String str2 = new String("study");
        final String str3 = "study";

        assertAll(
                () -> assertSame(str1.hashCode(), str2.hashCode()),
                () -> assertSame(str2.hashCode(), str3.hashCode()),
                () -> assertSame(str1.hashCode(), str3.hashCode())
        );
    }

    @DisplayName("String 인스턴스 변수의 값이 같다면 hashCode의 값은 같지만 동일하지는 않습니다.")
    @Test
    void testHashCode2() {
        final String str1 = new String(new char[]{'s', 't', 'u', 'd', 'y'});
        final String str2 = new String("study");
        final String str3 = "study";

        final Object obj1 = str1.hashCode();
        final Object obj2 = str2.hashCode();
        final Object obj3 = str3.hashCode();

        assertAll(
                () -> assertNotSame(obj1, obj2),
                () -> assertNotSame(obj2, obj3),
                () -> assertNotSame(obj1, obj3)
        );
    }

    @DisplayName("StringBuffer의 값을 String의 값으로 변환할 수 있습니다.")
    @Test
    void testStringBuffer() {
        final StringBuffer buffer = new StringBuffer("study");
        final String str1 = new String(buffer);

        assertEquals("study", str1);
    }

    @DisplayName("StringBuilder 값을 String의 값으로 변환할 수 있습니다.")
    @Test
    void testStringBuilder() {
        final StringBuilder buffer = new StringBuilder("study");
        final String str1 = new String(buffer);

        assertEquals("study", str1);
    }

    @DisplayName("String의 비어 있는 값은 isEmpty()와 동일합니다.")
    @Test
    void testIsEmpty() {
        final String emptyString1 = new String();
        final String emptyString2 = new String("");
        final String emptyString3 = "";
        final String str1 = "study";

        assertAll(
                () -> assertTrue(emptyString1.isEmpty()),
                () -> assertTrue(emptyString2.isEmpty()),
                () -> assertTrue(emptyString3.isEmpty()),
                () -> assertFalse(str1.isEmpty())
        );
    }

    @DisplayName("String의 값을 한 글자 char 타입으로 받을 수 있습니다.")
    @Test
    void testCharAt() {
        final String str1 = "study";

        assertAll(
                () -> assertEquals('s', str1.charAt(0)),
                () -> assertEquals('t', str1.charAt(1)),
                () -> assertEquals('u', str1.charAt(2)),
                () -> assertEquals('d', str1.charAt(3)),
                () -> assertEquals('y', str1.charAt(4))
        );
    }

    @DisplayName("String의 charAt 범위를 벗어나면 예외가 발생합니다.")
    @Test
    void testCharAt_exception() {
        final String str1 = "study";

        StringIndexOutOfBoundsException exception = assertThrows(StringIndexOutOfBoundsException.class, () -> str1.charAt(-1));
        String message = exception.getMessage();

        assertEquals("String index out of range: -1", message);
    }

    @Test
    void codePointTest() {
        final String str1 = "study";
        str1.codePointAt(0);

    }

    @Test
    void testStringByte() {
        final byte[] bytes = new byte[]{127};
        final String str1 = new String(bytes);
        System.out.println(str1);
    }
}