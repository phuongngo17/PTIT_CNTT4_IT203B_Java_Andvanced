package session03.baitap05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bai05 {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        users.add(new User("alexander"));
        users.add(new User("charlotte"));
        users.add(new User("Benjamin"));
        users.add(new User("Tom"));
        users.add(new User("Anna"));
        users.add(new User("Christopher"));

        System.out.println("3 username dài nhất: ");

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.getUsername().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.getUsername()));
    }
}
