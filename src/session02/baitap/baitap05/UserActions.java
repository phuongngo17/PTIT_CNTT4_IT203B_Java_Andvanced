package session02.baitap.baitap05;

public interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User activity: " + activity);
    }

}
