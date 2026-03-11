package session04.baitap06;

import java.time.LocalDate;

public class UserProfile {

    private String email;
    private LocalDate birthday;

    public UserProfile(String email, LocalDate birthday) {
        this.email = email;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}