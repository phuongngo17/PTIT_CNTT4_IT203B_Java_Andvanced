package session02.baitap.baitap03;

@FunctionalInterface
public interface Authenticatable {

    // phương thức trừu tượng
    String getPassword();

    // default method
    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    // static method
    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword;
    }
}