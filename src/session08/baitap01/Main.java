package session08.baitap01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("5. Thoát");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    HardwareConnection.getInstance();
                    break;

                case 2:
                    System.out.println("Chọn loại: 1.Đèn 2.Quạt 3.Điều hòa");
                    int type = sc.nextInt();

                    DeviceFactory factory = null;

                    switch (type) {
                        case 1: factory = new LightFactory(); break;
                        case 2: factory = new FanFactory(); break;
                        case 3: factory = new AirConditionerFactory(); break;
                    }

                    if (factory != null) {
                        Device device = factory.createDevice();
                        devices.add(device);
                    }
                    break;

                case 3:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị");
                        break;
                    }

                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }

                    int onIndex = sc.nextInt() - 1;
                    devices.get(onIndex).turnOn();
                    break;

                case 4:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị");
                        break;
                    }

                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }

                    int offIndex = sc.nextInt() - 1;
                    devices.get(offIndex).turnOff();
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }
}