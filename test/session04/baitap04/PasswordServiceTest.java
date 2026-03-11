package session04.baitap04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceTest {

    PasswordService service = new PasswordService();

    @Test
    void shouldReturnStrongWhenPasswordMeetsAllConditions() {
        String result = service.evaluatePasswordStrength("Abc123!@");
        assertEquals("Mạnh", result);
    }

    @Test
    void shouldReturnMediumWhenMissingUppercase() {
        String result = service.evaluatePasswordStrength("abc123!@");
        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingLowercase() {
        String result = service.evaluatePasswordStrength("ABC123!@");
        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingDigit() {
        String result = service.evaluatePasswordStrength("Abcdef!@");
        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnMediumWhenMissingSpecialCharacter() {
        String result = service.evaluatePasswordStrength("Abc12345");
        assertEquals("Trung bình", result);
    }

    @Test
    void shouldReturnWeakWhenPasswordTooShort() {
        String result = service.evaluatePasswordStrength("Ab1!");
        assertEquals("Yếu", result);
    }

    @Test
    void shouldReturnWeakWhenOnlyLowercase() {

        assertAll(
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("password")),
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("ABC12345"))
        );
    }
}