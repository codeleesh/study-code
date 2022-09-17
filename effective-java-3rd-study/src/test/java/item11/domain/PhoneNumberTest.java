package item11.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @DisplayName("해시코드 재정의하지 않아 값을 찾지 못한다.")
    @Test
    void findByNull() {

        Map<PhoneNumber, String> phone = new HashMap<>();
        phone.put(PhoneNumber.of(707, 867, 5309), "lovethefeel");

        assertEquals(phone.get(PhoneNumber.of(707, 867, 5309)), "lovethefeel");
    }

    @DisplayName("toString 정의하지 않고 호출한다.")
    @Test
    void printNoToString() {

        PhoneNumber phoneNumber = PhoneNumber.of(707, 867, 5309);
        System.out.println("phoneNumber : " + phoneNumber);
    }

    @DisplayName("Cloneable 인터페이스를 구현하지 않고 clone 메서드를 호출하면 CloneNotSupportedException 된다.")
    @Disabled(value = "Cloneable 인터페이스를 구현하지 않았을 경우에만 테스트가 성공한다.")
    @Test
    void cloneExceptionTest() throws CloneNotSupportedException {

        PhoneNumber phoneNumber = PhoneNumber.of(707, 867, 5309);

        assertThatThrownBy(() -> phoneNumber.clone())
                .isInstanceOf(CloneNotSupportedException.class);
    }

    @DisplayName("clone한 객체의 내용을 변경하면 원본 객체의 내용도 변경된다?")
    @Test
    void cloneTest() throws CloneNotSupportedException {

        PhoneNumber phoneNumber = PhoneNumber.of(707, 867, 5309);

        PhoneNumber copyPhoneNumber = (PhoneNumber) phoneNumber.clone();

        System.out.println("phoneNumber : " + phoneNumber);
        System.out.println("copyPhoneNumber : " + copyPhoneNumber);

        copyPhoneNumber.setAreaCode(111);

        System.out.println("phoneNumber : " + phoneNumber);
        System.out.println("copyPhoneNumber : " + copyPhoneNumber);
    }
}