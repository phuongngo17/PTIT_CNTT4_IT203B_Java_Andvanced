package session04.baitap05;

public class User {

    private String username;
    private Role role;

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}