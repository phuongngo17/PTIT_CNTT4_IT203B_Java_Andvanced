package session04.baitap03;

public class UserProcessor {

    public String processEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email phải chứa @");
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[1].isEmpty()) {
            throw new IllegalArgumentException("Email phải chứa tên miền");
        }

        return email.toLowerCase();
    }
}