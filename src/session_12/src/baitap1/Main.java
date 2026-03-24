package baitap1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập mã bác sĩ: ");
        String code = scanner.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String pass = scanner.nextLine();

        DoctorService.login(code, pass);

        scanner.close();
    }
}
