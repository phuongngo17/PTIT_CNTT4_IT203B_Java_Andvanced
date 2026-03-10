package session03.baitap04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Bai04 {
    record User(String username, String email) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@example.com"),
                new User("alice", "alice@example.com"),
                new User("charlie", "charlie@example.com")
        );
        Set<String> set = users.stream()
                .map(User::email)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
}
