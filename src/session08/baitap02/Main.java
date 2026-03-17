package session08.baitap02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor sensor = new ThermometerAdapter(oldThermometer);
        SmartHomeFacade home = new SmartHomeFacade(sensor);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem nhiệt độ");
            System.out.println("2. Rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("4. Thoát");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    home.getCurrentTemperature();
                    break;
                case 2:
                    home.leaveHome();
                    break;
                case 3:
                    home.sleepMode();
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
