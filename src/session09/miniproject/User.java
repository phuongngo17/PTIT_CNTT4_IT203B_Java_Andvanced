package session09.miniproject;

import java.util.List;
import java.util.Scanner;

public class User {
    private String userId;
    private String userName;
    private int age;
    private String role;
    private double score;

    public User() {}

    public User(String userId, String userName, int age, String role, double score) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.role = role;
        this.score = score;
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public int getAge() { return age; }
    public String getRole() { return role; }
    public double getScore() { return score; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setAge(int age) { this.age = age; }
    public void setRole(String role) { this.role = role; }
    public void setScore(double score) { this.score = score; }

    public void inputData(Scanner sc, List<User> users) {

        // ID
        while (true) {
            System.out.print("Nhập mã (Uxxx): ");
            String id = sc.nextLine();

            boolean exists = users.stream()
                    .anyMatch(u -> u.getUserId().equalsIgnoreCase(id));

            if (!Validator.isValidUserId(id)) {
                System.err.println("Sai format U001!");
            } else if (exists) {
                System.err.println("Mã đã tồn tại!");
            } else {
                this.userId = id;
                break;
            }
        }

        System.out.print("Tên: ");
        this.userName = sc.nextLine();

        // Age
        while (true) {
            try {
                System.out.print("Tuổi: ");
                int age = Integer.parseInt(sc.nextLine());
                if (Validator.isValidAge(age)) {
                    this.age = age;
                    break;
                }
                System.err.println(">=18!");
            } catch (Exception e) {
                System.err.println("Sai định dạng!");
            }
        }

        // Role
        while (true) {
            System.out.print("Role (USER/ADMIN): ");
            String role = sc.nextLine().toUpperCase();
            if (Validator.isValidRole(role)) {
                this.role = role;
                break;
            }
            System.err.println("Sai role!");
        }

        // Score
        while (true) {
            try {
                System.out.print("Score: ");
                double score = Double.parseDouble(sc.nextLine());
                if (Validator.isValidScore(score)) {
                    this.score = score;
                    break;
                }
                System.err.println("0-10!");
            } catch (Exception e) {
                System.err.println("Sai định dạng!");
            }
        }
    }

    public void displayData() {
        System.out.printf("| %-5s | %-15s | %-3d | %-6s | %-5.2f |\n",
                userId, userName, age, role, score);
    }
}
