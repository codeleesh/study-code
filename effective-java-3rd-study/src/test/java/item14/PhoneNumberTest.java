package item14;

import item11.domain.PhoneNumber;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    @Test
    void phoneNumber_compare_test() {

        PhoneNumber phoneNumber = PhoneNumber.of(708, 111, 5310);

        PhoneNumber comparePhoneNumber = PhoneNumber.of(707, 867, 5309);

        int result = phoneNumber.compareTo(comparePhoneNumber);

        System.out.println("result = " + result);
    }
}
