package session08.baitap05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);

        SleepModeCommand sleep = new SleepModeCommand();
        sleep.addCommand(new LightOffCommand(light));
        sleep.addCommand(new ACSetTempCommand(ac, 28));
        sleep.addCommand(new FanLowCommand(fan));

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Chế độ ngủ");
            System.out.println("2. Thay đổi nhiệt độ");
            System.out.println("3. Xem trạng thái");
            System.out.println("4. Thoát");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sleep.execute();
                    break;
                case 2:
                    System.out.println("Nhập nhiệt độ:");
                    int temp = sc.nextInt();
                    sensor.setTemperature(temp);
                    break;
                case 3:
                    fan.showStatus();
                    ac.showStatus();
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}