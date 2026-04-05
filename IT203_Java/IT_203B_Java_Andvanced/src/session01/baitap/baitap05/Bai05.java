package session01.baitap.baitap05;

public class Bai05 {

    public static void main(String[] args) {

        User user = new User();

        try {
            user.setAge(-10);
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        }

        System.out.println("Chương trình vẫn tiếp tục chạy");
    }
}