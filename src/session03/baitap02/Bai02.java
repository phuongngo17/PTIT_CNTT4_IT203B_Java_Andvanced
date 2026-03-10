package session03.baitap02;

import java.util.List;

public class Bai02 {
    record User(String username, String email) {}
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@example.com"),
                new User("bob", "bob@example.com"),
                new User("charlie", "charlie@example.com")
        );
        users.stream()
                .filter(user -> user.email().endsWith("@gmail.com"))
                .forEach(System.out::println);
    }
}
