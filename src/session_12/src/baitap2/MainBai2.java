package baitap2;

import java.util.Scanner;

public class MainBai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập ID bệnh nhân: ");
        int patientId = scanner.nextInt();

        System.out.print("Nhập nhiệt độ: ");
        double temp = scanner.nextDouble();

        System.out.print("Nhập nhịp tim: ");
        int heartRate = scanner.nextInt();

        VitalService.updateVitals(patientId, temp, heartRate);

        scanner.close();
    }
}