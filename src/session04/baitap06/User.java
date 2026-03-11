package session04.baitap06;

import java.time.LocalDate;

public class User {

    private String email;
    private LocalDate birthday;

    public User(String email, LocalDate birthday) {
        this.email = email;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}