package session01.baitap.baitap02;

import java.util.Scanner;

public class Bai02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập tổng số người dùng: ");
            int totalUsers = sc.nextInt();

            System.out.print("Nhập số nhóm muốn chia: ");
            int groups = sc.nextInt();

            int usersPerGroup = totalUsers / groups;

            System.out.println("Mỗi nhóm có: " + usersPerGroup + " người");

        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0");
        }

        System.out.println("Chương trình vẫn tiếp tục chạy");
        sc.close();
    }
}
