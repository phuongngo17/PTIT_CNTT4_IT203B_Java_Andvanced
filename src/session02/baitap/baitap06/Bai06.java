package session02.baitap.baitap06;

public class Bai06 {
    public static void main(String[] args) {
        User user = new User("trang");
        UserProcessor processor = UserUtils::convertToUpperCase;
        String result = processor.process(user);
        System.out.println(result);
    }

}

