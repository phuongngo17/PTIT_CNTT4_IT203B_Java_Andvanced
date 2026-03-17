package session08.baitap03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RemoteControl remote = new RemoteControl();
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("4. Thoát");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Chọn nút:");
                    int slot = sc.nextInt();
                    System.out.println("1.Light ON 2.Light OFF 3.Fan ON 4.Fan OFF 5.AC set temp");
                    int type = sc.nextInt();
                    Command command = null;

                    switch (type) {
                        case 1: command = new LightOnCommand(light); break;
                        case 2: command = new LightOffCommand(light); break;
                        case 3: command = new FanOnCommand(fan); break;
                        case 4: command = new FanOffCommand(fan); break;
                        case 5:
                            System.out.println("Nhập nhiệt độ:");
                            int temp = sc.nextInt();
                            command = new ACSetTemperatureCommand(ac, temp);
                            break;
                    }

                    remote.setCommand(slot, command);
                    break;

                case 2:
                    System.out.println("Nhấn nút:");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;

                case 3:
                    remote.undo();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}