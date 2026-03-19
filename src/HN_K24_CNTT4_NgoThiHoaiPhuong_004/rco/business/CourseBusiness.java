package HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.business;

import HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseBusiness {
    private static CourseBusiness instance;
    private List<Course> listcourse;
    private CourseBusiness() {
        listcourse = new ArrayList<>();
    }
    public static CourseBusiness getInstance() {
        if (instance == null) {
            instance = new CourseBusiness();

        }
        return instance;
    }

    // hiển thị
    public void displayAll(){
        if (listcourse.isEmpty()){
            System.out.println("Danh sách rỗng");
            return;
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("%-6s | %-20S | %-6s | %-10s | %-15s | %-10s\n",
                "Id", "Tên", "Số tín chỉ", "Học phí", "Giảng viên", "Trạng thái");
        System.out.println("------------------------------------------------------");
    }
    // thêm
    public void addCourse(Scanner scanner){
        do{
            Course c =  new Course();
            c.inputData(scanner, listcourse);
            listcourse.add(c);
            System.out.println("Thêm thành công");
            System.out.println("Tiếp tục ? (y/n): ");
        }while (scanner.nextLine().equalsIgnoreCase("y"));
    }

    // cập nhật
    public void updateCourse(String id, Scanner scanner){
        Optional<Course> otp = listcourse.stream()
                .filter(c->c.getCourseId().equals(id)).findFirst();
        if(otp.isEmpty()){
            System.out.println("mã khóa học không tồn tại");
            return;
        }
        Course c = otp.get();
        System.out.println("1. Tên");
        System.out.println("2. Số tín chỉ");
        System.out.println("3. Học phí");
        System.out.println("4. Giảng viên");
        System.out.println("5.Trạng thái");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                System.out.println("Tên mới: ");
                c.setCourseName(scanner.nextLine());
                break;
            case 2:
                System.out.println("Số tín chỉ: ");
                c.setCredit(Integer.parseInt(scanner.nextLine()));
                break;
            case 3:
                System.out.println("Học phí: ");
                c.setTuitionFee(Double.parseDouble(scanner.nextLine()));
                break;
            case 4:
                System.out.println("Giảng viên: ");
                c.setInstructor(scanner.nextLine());
                break;
            case 5:
                System.out.println("Trạng thái: ");
                c.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                break;

        }
    }
    // xóa
    public void deleteCourse(String id){
        boolean removed = listcourse.removeIf(c -> c.getCourseId().equals(id));
        if(!removed){
            System.out.println("Mã khóa học không tồn tại");
        }
    }
    // tìm kiếm
    public void searchByInstructor(String keyword){
        List<Course> result = listcourse.stream()
                .filter(c-> c.getInstructor().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        if(result.isEmpty()){
            System.out.println("không có kết quả");
        }else{
            result.forEach(Course:: displayData);
            System.out.println("Tổng: " + result.size());
        }

    }
    //Lọc khóa học đang mở
    public void filterActiveCourse(){
        listcourse.stream()
                .filter(Course::isStatus)
                .forEach(Course:: displayData);
    }
    //sắp xếp giảm dần
    public void sortByFeeDesc(){
        listcourse.stream()
                .sorted((a,b) -> Double.compare(b.getTuitionFee(), a.getTuitionFee()))
                .forEach(Course:: displayData);
    }
}