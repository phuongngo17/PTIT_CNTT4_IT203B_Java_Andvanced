package HN_K24_CNTT4_NgoThiHoaiPhuong_004.rco.entity;
import java.util.List;
import java.util.Scanner;

public class Course {
    private String courseId;
    private String courseName;
    private int credit;
    private double tuitionFee;
    private String instructor;
    private boolean status;

    public Course() {
    }

    public Course(String courseId, String courseName, int credit, double tuitionFee, String instructor, boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.tuitionFee = tuitionFee;
        this.instructor = instructor;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, List<Course> courses) {
        //mã khóa học, định dạng  và không được trùng
        while (true) {
            System.out.print("Nhập mã (CO001...): ");
            courseId = scanner.next();

            boolean exists = courses.stream()
                    .anyMatch(c -> c.getCourseId().equalsIgnoreCase(courseId));

            if (!courseId.matches("CO\\d{3}")) {
                System.err.println("Sai định dạng (CO001)");
            }else if (exists) {
                System.err.println("Mã bị trùng");
            } else {
                break;
            }
        }

        //tên khóa học, không được để trống
        while (true) {
            System.out.println("Nhập tên khóa học: ");
            String name = scanner.nextLine();
            if(name.length() >= 5){
                break;
            }
        }
        //số tín chỉ > 0 và < 10
        while (true) {
            System.out.println("Nhập số tín chỉ: ");
            credit = Integer.parseInt(scanner.nextLine());
            if (credit < 0 || credit > 10) {
                break;
            }

        }
        //học phí > 0
        while (true) {
            System.out.println("Nhập học phí: ");
            Double tuition = Double.parseDouble(scanner.nextLine());
            if (tuition > 0) {
                break;
            }
        }
        //giảng viên phụ trách k được để trống
        while (true) {
            System.out.println("Nhập giảng viên phụ trách: ");
            instructor = scanner.nextLine();
            if(!instructor.isBlank()){
                break;
            }
        }
        //trạng thái khóa học
        while (true) {
            System.out.println("Trạng thái (true/false): ");
            Boolean status = Boolean.parseBoolean(scanner.nextLine());
        }
    }
    public void displayData() {
                System.out.printf("| %-6s | %-20s | %-6d | %-15s | %-10.2f | %-10s\n",
                courseId, courseName, credit, tuitionFee, instructor, status ? "Đang mở " : "Đã đóng");
    }
}

