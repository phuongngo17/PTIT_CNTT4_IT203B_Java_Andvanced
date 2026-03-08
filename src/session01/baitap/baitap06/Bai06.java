package session01.baitap.baitap06;

import java.io.IOException;

public class Bai06 {

    public static void main(String[] args) {

        User user = new User("Phuong");
        try {
            user.setAge(-5);
        } catch (InvalidAgeException e) {

            Logger.logError(e.getMessage());
        }

        if (user.getName() != null) {
            System.out.println("Tên người dùng: " + user.getName());
        }

        UserService service = new UserService();
        try {

            service.saveToFile();

        } catch (IOException e) {

            Logger.logError("Lỗi hệ thống khi ghi file: " + e.getMessage());
        }
        System.out.println("Chương trình kết thúc an toàn");
    }
}
