package session04.baitap02;

import org.junit.jupiter.api.Test;
import session04.baitap02.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    @Test
    void TC01_validAgeBoundary() {
        int age = 18;
        boolean result = UserService.checkRegistrationAge(age);
        assertEquals(true, result);
    }

    @Test
    void TC02_ageBelowRequirement() {
        int age = 17;
        boolean result = UserService.checkRegistrationAge(age);

        assertEquals(false, result);
    }

    @Test
    void TC03_negativeAge() {

        int age = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            UserService.checkRegistrationAge(age);
        });
    }
}