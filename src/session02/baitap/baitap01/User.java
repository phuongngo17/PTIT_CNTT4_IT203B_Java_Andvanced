package session02.baitap.baitap01;

public class User {
    //Kiểm tra xem một User có phải là Admin hay không (trả về true/false).
    //Predicate<User> isAdmin = user -> user.getRole().equals("ADMIN");
    //nhận vào User
    //kiểm tra điều kiện
    //trả về true / false

    //Chuyển đổi một đối tượng User thành một chuỗi String chứa thông tin username.
    //Function<User, String> getUsername = user -> user.getUsername();
    //input: User
    //output: String

    //In thông tin chi tiết của User ra màn hình Console.
    //Consumer<User> printUser = user -> System.out.println(user);
    //Nhận User
    //Thực hiện hành động in ra màn hình
    //Không trả về gì

    //Khởi tạo một đối tượng User mới với các giá trị mặc định.
    //Supplier<User> createUser = () -> new User("guest", "123456");
    //Không cần tham số
    //Tạo và trả về một User
}
