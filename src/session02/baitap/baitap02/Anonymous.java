package session02.baitap.baitap02;

public class Anonymous {
    PasswordValidator validator = new PasswordValidator() {
        @Override
        public boolean isValid(String password) {
            return password.length() >= 8;
        }
    };
//    PasswordValidator validator = (String password) -> {
//        return password.length() >= 8;
//    };
//    PasswordValidator validator = (password) -> {
//        return password.length() >= 8;
//    };
//    PasswordValidator validator = password -> password.length() >= 8;
}
