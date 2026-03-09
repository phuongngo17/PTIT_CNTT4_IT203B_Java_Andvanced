package session02.baitap.baitap04;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;

    public User() {
        this.username = "defaultUser";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
//    List<User> users = new ArrayList<>();
//    users.add(new User("An"));
//    users.add(new User("Binh"));
//    users.add(new User("Chi"));

//    (user) -> user.getUsername() (Tham chiếu instance method của đối tượng bất kỳ thuộc kiểu cụ thể).
//    Lambda:
//    users.stream()
//            .map(user -> user.getUsername())
//            .forEach(System.out::println);

//        Method Reference:
//        users.stream()
//            .map(User::getUsername)
//            .forEach(System.out::println);

//    (s) -> System.out.println(s)
//    Lambda:
//     users.stream()
//         .map(User::getUsername)
//         .forEach(s -> System.out.println(s));
//    Method Reference:
//     users.stream()
//          .map(User::getUsername)
//          .forEach(System.out::println);

//    () -> new User()
//    Lambda:
//    Supplier<User> supplier = () -> new User();
//    Method Reference:
//    Supplier<User> supplier = User::new;
}
