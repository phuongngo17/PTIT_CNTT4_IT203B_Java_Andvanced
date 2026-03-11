package session04.baitap06;

import java.time.LocalDate;
import java.util.List;

public class UserService {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {

        if (newProfile.getBirthday().isAfter(LocalDate.now())) {
            return null;
        }

        String newEmail = newProfile.getEmail();

        if (!newEmail.equals(existingUser.getEmail())) {
            for (User u : allUsers) {
                if (u.getEmail().equals(newEmail)) {
                    return null;
                }
            }
        }

        existingUser.setEmail(newEmail);
        existingUser.setBirthday(newProfile.getBirthday());

        return existingUser;
    }
}
