package session04.baitap03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProcessorTest {

    private UserProcessor userProcessor;

    @BeforeEach
    void setUp() {
        userProcessor = new UserProcessor();
    }

    @Test
    void shouldReturnSameEmailWhenEmailIsValid() {
        String result = userProcessor.processEmail("user@gmail.com");
        assertEquals("user@gmail.com", result);
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingAtSymbol() {
        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail("usergmail.com");
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailMissingDomain() {
        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.processEmail("user@");
        });
    }

    @Test
    void shouldConvertEmailToLowercase() {
        String result = userProcessor.processEmail("Example@Gmail.com");
        assertEquals("example@gmail.com", result);
    }
}