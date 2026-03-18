package session09.th;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n------ QUẢN LÝ SẢN PHẨM ------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm ");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("1. Physical | 2. Digital");
                    int type = Integer.parseInt(sc.nextLine());
                    Product p = ProductFactory.createProduct(type, sc);
                    db.addProduct(p);
                    break;

                case 2:
                    List<Product> list = db.getProductList();
                    for (Product pr : list) {
                        pr.displayInfo();
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID cần sửa: ");
                    String idUpdate = sc.nextLine();
                    Product old = db.getProduct(idUpdate);

                    if (old != null) {
                        System.out.println("Nhập lại thông tin:");
                        System.out.println("1.Physical | 2.Digital");
                        int newType = Integer.parseInt(sc.nextLine());

                        Product newProduct = ProductFactory.createProduct(newType, sc);
                        db.deleteProduct(idUpdate);
                        db.addProduct(newProduct);
                    } else {
                        System.out.println("Không tìm thấy");
                    }
                    break;
                case 4:
                    System.out.print("Nhập ID cần xóa: ");
                    String idDelete = sc.nextLine();
                    db.deleteProduct(idDelete);
                    break;
                case 5:
                    System.out.println("Thoát");
                    return;
                default:
                    System.out.println("Sai lựa chọn");
            }
        }
    }
}