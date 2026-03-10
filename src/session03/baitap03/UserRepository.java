package session03.baitap03;

import java.util.List;
import java.util.Optional;

public class UserRepository{
    record User(String username, String email) {}
    private List<User> users = List.of(
            new User("alice", "alice@example.com"),
            new User("bob", "bob@example.com"),
            new User("charlie", "charlie@example.com")
    );
    Optional<User> findUserByUsername(String username ){
        return users.stream().filter(user -> user.username().equals(username)).findFirst();
    }
}
