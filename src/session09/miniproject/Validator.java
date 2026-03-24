package session09.miniproject;

public class Validator {

    public static boolean isValidUserId(String id) {
        return id.matches("U\\d{3}");
    }

    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    public static boolean isValidRole(String role) {
        return role.equalsIgnoreCase("USER") || role.equalsIgnoreCase("ADMIN");
    }

    public static boolean isValidScore(double score) {
        return score >= 0 && score <= 10;
    }
}