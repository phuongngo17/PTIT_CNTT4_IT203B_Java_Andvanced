package session04.baitap06;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService service = new UserService();

    @Test
    void shouldUpdateProfileWhenEmailAndBirthdayValid() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        UserProfile newProfile =
                new UserProfile("new@gmail.com", LocalDate.of(1999,1,1));

        List<User> users = new ArrayList<>();

        User result = service.updateProfile(existing, newProfile, users);

        assertNotNull(result);
        assertEquals("new@gmail.com", result.getEmail());
    }

    @Test
    void shouldRejectUpdateWhenBirthdayInFuture() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        UserProfile newProfile =
                new UserProfile("new@gmail.com", LocalDate.now().plusDays(1));

        List<User> users = new ArrayList<>();

        User result = service.updateProfile(existing, newProfile, users);

        assertNull(result);
    }

    @Test
    void shouldRejectUpdateWhenEmailAlreadyExists() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        User anotherUser =
                new User("duplicate@gmail.com", LocalDate.of(1995,1,1));

        List<User> users = new ArrayList<>();
        users.add(anotherUser);

        UserProfile newProfile =
                new UserProfile("duplicate@gmail.com", LocalDate.of(1999,1,1));

        User result = service.updateProfile(existing, newProfile, users);

        assertNull(result);
    }

    @Test
    void shouldAllowUpdateWhenEmailSameAsCurrent() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        UserProfile newProfile =
                new UserProfile("user@gmail.com", LocalDate.of(1998,1,1));

        List<User> users = new ArrayList<>();

        User result = service.updateProfile(existing, newProfile, users);

        assertNotNull(result);
        assertEquals(LocalDate.of(1998,1,1), result.getBirthday());
    }

    @Test
    void shouldUpdateWhenUserListEmpty() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        UserProfile newProfile =
                new UserProfile("valid@gmail.com", LocalDate.of(1997,1,1));

        List<User> users = new ArrayList<>();

        User result = service.updateProfile(existing, newProfile, users);

        assertNotNull(result);
    }

    @Test
    void shouldRejectWhenEmailDuplicateAndBirthdayFuture() {

        User existing = new User("user@gmail.com", LocalDate.of(2000,1,1));

        User anotherUser =
                new User("duplicate@gmail.com", LocalDate.of(1995,1,1));

        List<User> users = new ArrayList<>();
        users.add(anotherUser);

        UserProfile newProfile =
                new UserProfile("duplicate@gmail.com", LocalDate.now().plusDays(5));

        User result = service.updateProfile(existing, newProfile, users);

        assertNull(result);
    }
}