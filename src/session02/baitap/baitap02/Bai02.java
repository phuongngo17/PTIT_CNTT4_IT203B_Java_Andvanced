package session02.baitap.baitap02;

public class Bai02 {
    public static void main(String[] args) {

        PasswordValidator validator = password -> password.length() >= 8;

        System.out.println(validator.isValid("12345678"));
        System.out.println(validator.isValid("1234"));
    }
}