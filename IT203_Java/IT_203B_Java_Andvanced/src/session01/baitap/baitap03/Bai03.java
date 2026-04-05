package session01.baitap.baitap03;

public class Bai03 {
    public static void main(String[] args){
        User user = new User();
        try {
            user.setAge(-5);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Chương trình vẫn tiếp tục chạy");
    }
}
