package session02.baitap.baitap05;

public class SuperAdmin implements UserActions, AdminActions {
    @Override
    public void logActivity(String activity) {
        AdminActions.super.logActivity(activity);

    }
}
