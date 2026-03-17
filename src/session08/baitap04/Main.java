package session08.baitap04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TemperatureSensor sensor = new TemperatureSensor();
        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Đăng ký thiết bị");
            System.out.println("2. Set nhiệt độ");
            System.out.println("3. Thoát");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("1.Quạt 2.Máy tạo ẩm");
                    int type = sc.nextInt();

                    if (type == 1) {
                        sensor.attach(fan);
                        System.out.println("Quạt đã đăng ký nhận thông báo");
                    } else {
                        sensor.attach(humidifier);
                        System.out.println("Máy tạo ẩm đã đăng ký");
                    }
                    break;

                case 2:
                    System.out.println("Nhập nhiệt độ:");
                    int temp = sc.nextInt();
                    sensor.setTemperature(temp);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}