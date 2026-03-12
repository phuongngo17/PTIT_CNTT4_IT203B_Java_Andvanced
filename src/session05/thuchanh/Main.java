package session05.thuchanh;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {
            System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            try {

                switch (choice) {

                    case 1:

                        System.out.print("Nhập ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Tên sản phẩm: ");
                        String name = scanner.nextLine();

                        System.out.print("Giá: ");
                        double price = scanner.nextDouble();

                        System.out.print("Số lượng: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Danh mục: ");
                        String category = scanner.nextLine();

                        Product p = new Product(id,name,price,quantity,category);

                        service.addProduct(p);

                        break;

                    case 2:

                        service.displayProducts();
                        break;

                    case 3:

                        System.out.print("Nhập ID sản phẩm: ");
                        int updateId = scanner.nextInt();

                        System.out.print("Số lượng mới: ");
                        int newQty = scanner.nextInt();

                        service.updateQuantity(updateId,newQty);

                        break;

                    case 4:

                        service.removeOutOfStock();
                        break;

                    case 5:

                        System.out.println("Thoát chương trình...");
                        return;

                    default:

                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (InvalidProductException e) {

                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
