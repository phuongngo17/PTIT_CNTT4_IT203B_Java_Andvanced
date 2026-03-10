package session03.baitap01;

import java.util.List;

public class Bai01 {
    record User(String username, String email, Status status) {}
    enum Status {
        ACTIVE, INACTIVE
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@example.com", Status.ACTIVE),
                new User("bob", "bob@example.com", Status.INACTIVE),
                new User("charlie", "charlie@example.com", Status.ACTIVE)
        );

        users.forEach(System.out::println);
    }
}

