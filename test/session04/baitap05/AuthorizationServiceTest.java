package session04.baitap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {

    AuthorizationService service = new AuthorizationService();
    User user;

    @AfterEach
    void cleanUp() {
        user = null;
    }

    @Test
    void adminCanDeleteUser() {
        user = new User("admin", Role.ADMIN);
        boolean result = service.canPerformAction(user, Action.DELETE_USER);
        assertTrue(result);
    }

    @Test
    void adminCanLockUser() {
        user = new User("admin", Role.ADMIN);
        boolean result = service.canPerformAction(user, Action.LOCK_USER);
        assertTrue(result);
    }

    @Test
    void adminCanViewProfile() {
        user = new User("admin", Role.ADMIN);
        boolean result = service.canPerformAction(user, Action.VIEW_PROFILE);
        assertTrue(result);
    }


    @Test
    void moderatorCannotDeleteUser() {
        user = new User("mod", Role.MODERATOR);
        boolean result = service.canPerformAction(user, Action.DELETE_USER);
        assertFalse(result);
    }

    @Test
    void moderatorCanLockUser() {
        user = new User("mod", Role.MODERATOR);
        boolean result = service.canPerformAction(user, Action.LOCK_USER);
        assertTrue(result);
    }

    @Test
    void moderatorCanViewProfile() {
        user = new User("mod", Role.MODERATOR);
        boolean result = service.canPerformAction(user, Action.VIEW_PROFILE);
        assertTrue(result);
    }


    @Test
    void userCannotDeleteUser() {
        user = new User("user", Role.USER);
        boolean result = service.canPerformAction(user, Action.DELETE_USER);
        assertFalse(result);
    }

    @Test
    void userCannotLockUser() {
        user = new User("user", Role.USER);
        boolean result = service.canPerformAction(user, Action.LOCK_USER);
        assertFalse(result);
    }

    @Test
    void userCanViewProfile() {
        user = new User("user", Role.USER);
        boolean result = service.canPerformAction(user, Action.VIEW_PROFILE);
        assertTrue(result);
    }
}