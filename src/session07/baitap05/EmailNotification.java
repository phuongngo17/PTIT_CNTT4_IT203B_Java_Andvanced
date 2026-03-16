package session07.baitap05;

public class EmailNotification implements NotificationService {
    @Override
    public void send(String message, String to) {
        System.out.println("Đã gửi email xác nhận");
    }
}
