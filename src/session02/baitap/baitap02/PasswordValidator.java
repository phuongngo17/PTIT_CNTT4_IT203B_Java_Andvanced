package session02.baitap.baitap02;

@FunctionalInterface
interface PasswordValidator {
    boolean isValid(String password);
}
