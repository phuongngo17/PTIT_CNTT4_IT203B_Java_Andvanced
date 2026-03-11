package session04.baitap01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    @Test
    void TC01_validUsername() {

        String username = "user123";

        boolean result = UserValidator.isValidUsername(username);

        assertTrue(result);
    }

    @Test
    void TC02_usernameTooShort() {

        String username = "abc";

        boolean result = UserValidator.isValidUsername(username);

        assertFalse(result);
    }

    @Test
    void TC03_usernameContainSpace() {

        String username = "user name";

        boolean result = UserValidator.isValidUsername(username);

        assertFalse(result);
    }
}