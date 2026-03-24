package session09.miniproject;

import java.util.Scanner;

public class UserManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserBusiness business = UserBusiness.getInstance();

        while (true) {
            try {
                System.out.println("********************* QUẢN LÝ NGƯỜI DÙNG *********************");
                System.out.println("1. Hiển thị danh sách toàn bộ người dùng");
                System.out.println("2. Thêm mới người dùng");
                System.out.println("3. Cập nhật thông tin người dùng theo mã");
                System.out.println("4. Xóa người dùng theo mã");
                System.out.println("5. Tìm kiếm người dùng theo tên");
                System.out.println("6. Lọc danh sách người dùng ADMIN");
                System.out.println("7. Sắp xếp danh sách theo điểm giảm dần");
                System.out.println("8. Thoát");

                System.out.print("Lựa chọn: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        business.displayList();
                        break;

                    case 2:
                        User user = new User();
                        user.inputData(sc, business.getUsers());
                        business.addUser(user);
                        break;

                    case 3:
                        System.out.print("Nhập ID: ");
                        business.updateUser(sc.nextLine(), sc);
                        break;

                    case 4:
                        System.out.print("Nhập ID: ");
                        business.deleteUser(sc.nextLine());
                        break;

                    case 5:
                        System.out.print("Nhập tên: ");
                        business.searchByName(sc.nextLine());
                        break;

                    case 6:
                        business.filterAdmin();
                        break;

                    case 7:
                        business.sortUsers();
                        break;

                    case 8:
                        System.out.println("Thoát chương trình!");
                        System.exit(0);

                    default:
                        System.err.println("Lựa chọn không hợp lệ!");
                }

            } catch (Exception e) {
                System.err.println("Lỗi nhập! Vui lòng thử lại.");
            }
        }
    }
}