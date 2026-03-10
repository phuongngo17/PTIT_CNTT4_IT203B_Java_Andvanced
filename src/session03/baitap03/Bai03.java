package session03.baitap03;

import java.util.Optional;

public class Bai03 {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        Optional<UserRepository.User> optionalUser = userRepository.findUserByUsername("alice");

        String message = optionalUser.map(u -> "Welcome " + u.username())
                .orElse("Guest login");
        System.out.println(message);
    }
}
