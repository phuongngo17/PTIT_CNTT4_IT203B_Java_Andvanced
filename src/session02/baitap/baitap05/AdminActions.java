package session02.baitap.baitap05;

public interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin activity: " + activity);
    }
}
