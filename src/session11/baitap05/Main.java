package session11.baitap05;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DoctorDao dao = new DoctorDao();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {

                case 1:
                    // SHOW ALL
                    List<Doctor> list = dao.getAllDoctors();
                    if (list.isEmpty()) {
                        System.out.println("⚠ Không có dữ liệu");
                    } else {
                        for (Doctor d : list) {
                            System.out.println(
                                    d.getId() + " | " +
                                            d.getName() + " | " +
                                            d.getSpecialization()
                            );
                        }
                    }
                    break;

                case 2:
                    // ADD
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine().trim();

                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Nhập chuyên khoa: ");
                    String spec = sc.nextLine().trim();

                    if (id.isEmpty() || name.isEmpty() || spec.isEmpty()) {
                        System.out.println("Không được để trống!");
                        break;
                    }

                    if (spec.length() > 50) {
                        System.out.println(" Chuyên khoa quá dài!");
                        break;
                    }

                    dao.addDoctor(new Doctor(id, name, spec));
                    break;

                case 3:
                    dao.countBySpecialization();
                    break;

                case 4:
                    System.out.println(" Thoát chương trình...");
                    return;

                default:
                    System.out.println("Chọn sai!");
            }
        }
    }
}