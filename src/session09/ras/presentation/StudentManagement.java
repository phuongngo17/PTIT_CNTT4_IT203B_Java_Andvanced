package session09.ras.presentation;

import session09.ras.business.StudentBusiness;
import session09.ras.entity.Student;
import session09.ras.helper.InputHelper;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {

        StudentBusiness studentBusiness = StudentBusiness.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("""
                        1. List all students
                        2. Add new student
                        3. Update student by ID
                        4. Find student by ID
                        5. Find students by name
                        6. Filter students by GPA
                        7. Sort students by GPA
                        8. Exit
                        """);

                System.out.print("Choose an option: ");
                int choice = InputHelper.inputInteger(scanner);

                switch (choice) {

                    // ===== 1. HIỂN THỊ =====
                    case 1:
                        studentBusiness.listAllStudents();
                        break;

                    // ===== 2. THÊM =====
                    case 2:
                        while (true) {
                            Student student = new Student();
                            student.inputData(scanner); // validate bên trong

                            studentBusiness.addStudent(student);

                            System.out.print("Add more? (y/n): ");
                            String check = scanner.nextLine();
                            if (!check.equalsIgnoreCase("y")) {
                                break;
                            }
                        }
                        break;

                    // ===== 3. UPDATE =====
                    case 3:
                        System.out.print("Enter student ID to update: ");
                        String idUpdate = scanner.nextLine();

                        Optional<Student> updateOpt = studentBusiness.findStudentById(idUpdate);

                        if (updateOpt.isPresent()) {
                            studentBusiness.updateStudent(updateOpt.get(), scanner);
                            System.out.println("Updated successfully!");
                        } else {
                            System.out.println("Student not found!");
                        }
                        break;

                    // ===== 4. TÌM THEO ID =====
                    case 4:
                        System.out.print("Enter student ID to search: ");
                        String idSearch = scanner.nextLine();

                        Optional<Student> searchOpt = studentBusiness.findStudentById(idSearch);

                        if (searchOpt.isPresent()) {
                            studentBusiness.showStudentsTable(List.of(searchOpt.get()));
                        } else {
                            System.out.println("Student not found!");
                        }
                        break;

                    // ===== 5. TÌM THEO TÊN =====
                    case 5:
                        System.out.print("Enter name to search: ");
                        String name = scanner.nextLine();

                        List<Student> list = studentBusiness.findStudentsByName(name);

                        if (list.isEmpty()) {
                            System.out.println("No students found!");
                        } else {
                            studentBusiness.showStudentsTable(list);
                        }
                        break;

                    // ===== 6. LỌC GPA =====
                    case 6:
                        double gpa;
                        while (true) {
                            try {
                                System.out.print("Enter GPA threshold: ");
                                gpa = InputHelper.inputDouble(scanner);

                                if (gpa < 0 || gpa > 10) {
                                    System.out.println("GPA phải từ 0-10! Nhập lại.");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Sai định dạng! Nhập lại.");
                            }
                        }
                        studentBusiness.filterStudentsByGpa(gpa);
                        break;

                    // ===== 7. SORT =====
                    case 7:
                        studentBusiness.sortStudentsByGpaDescending();
                        break;

                    // ===== 8. EXIT =====
                    case 8:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Lỗi nhập! Vui lòng nhập lại.");
            }
        }
    }
}