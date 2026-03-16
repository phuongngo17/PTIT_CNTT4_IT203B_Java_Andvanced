package session07.baitap05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("0. Thoát");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Mã: ");
                    String id = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Giá: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Danh mục: ");
                    String category = sc.nextLine();
                    Product p = new Product(id,name,price,category);
                    DataStore.products.add(p);
                    System.out.println("Đã thêm sản phẩm " + id);
                    break;
                case 0:
                    return;
            }

        }

    }

}
