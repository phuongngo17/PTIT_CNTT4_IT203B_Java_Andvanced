package HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.presentation;
import HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.business.CourseBusiness;
import HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.entity.Course;

import java.util.Scanner;

public class CourseManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseBusiness service = CourseBusiness.getInstance();
        while(true){
            System.out.println("---------Quản lý Khóa học-----------");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2.Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm giảng viên");
            System.out.println("6.Lọc khóa học đang mở");
            System.out.println("7.Sắp xếp học phí giảm dần");
            System.out.println("8. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    service.displayAll();
                    break;
                case 2:
                    service.addCourse(sc);
                    break;
                case 3:
                    System.out.println("Nhập id: ");
                    service.updateCourse(sc.nextLine(),sc);
                    break;
                case 4:
                    System.out.println("Nhập id: ");
                    service.deleteCourse(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Nhập keyword: ");
                    service.searchByInstructor(sc.nextLine());
                    break;
                case 6:
                    service.filterActiveCourse();
                    break;
                case 7:
                    service.sortByFeeDesc();
                    break;
                case 8:
                    break;
            }
        }
    }
}
