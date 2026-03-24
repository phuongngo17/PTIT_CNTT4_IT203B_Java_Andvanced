package session09.miniproject;

import java.util.*;
import java.util.stream.Collectors;

public class UserBusiness {

    private static UserBusiness instance;
    private List<User> users;

    private UserBusiness() {
        users = new ArrayList<>();
    }

    public static UserBusiness getInstance() {
        if (instance == null) {
            instance = new UserBusiness();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void displayList() {
        if (users.isEmpty()) {
            System.err.println("Danh sách rỗng!");
            return;
        }
        users.forEach(User::displayData);
    }

    public void addUser(User user) {
        boolean exists = users.stream()
                .anyMatch(u -> u.getUserId().equalsIgnoreCase(user.getUserId()));

        if (exists) {
            System.err.println("Mã người dùng đã tồn tại");
        } else {
            users.add(user);
            System.out.println("Thêm thành công!");
        }
    }

    public void updateUser(String id, Scanner sc) {
        Optional<User> opt = users.stream()
                .filter(u -> u.getUserId().equalsIgnoreCase(id))
                .findFirst();

        if (!opt.isPresent()) {
            System.err.println("Mã người dùng không tồn tại trong hệ thống");
            return;
        }

        User user = opt.get();

        System.out.println("1. Tên");
        System.out.println("2. Tuổi");
        System.out.println("3. Role");
        System.out.println("4. Score");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Tên mới: ");
                user.setUserName(sc.nextLine());
                break;
            case 2:
                System.out.print("Tuổi mới: ");
                user.setAge(Integer.parseInt(sc.nextLine()));
                break;
            case 3:
                System.out.print("Role mới: ");
                user.setRole(sc.nextLine().toUpperCase());
                break;
            case 4:
                System.out.print("Score mới: ");
                user.setScore(Double.parseDouble(sc.nextLine()));
                break;
            default:
                System.err.println("Sai lựa chọn!");
        }

        System.out.println("Cập nhật thành công!");
    }

    public void searchByName(String keyword) {
        List<User> result = users.stream()
                .filter(u -> u.getUserName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy!");
        } else {
            result.forEach(User::displayData);
            System.out.println("Tổng: " + result.size());
        }
    }

    public void deleteUser(String id) {
        int before = users.size();
        users.removeIf(u -> u.getUserId().equalsIgnoreCase(id));

        if (before == users.size()) {
            System.err.println("Mã người dùng không tồn tại trong hệ thống");
        } else {
            System.out.println("Xóa thành công!");
        }
    }

    public void filterAdmin() {
        users.stream()
                .filter(u -> u.getRole().equalsIgnoreCase("ADMIN"))
                .forEach(User::displayData);
    }

    public void sortUsers() {
        SortStrategy strategy = new SortByScoreDesc();
        users = strategy.sort(users);
        displayList();
    }
}