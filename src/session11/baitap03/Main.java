package session11.baitap03;

public class Main {
    public static void main(String[] args) {

        BedDao dao = new BedDao();

        System.out.println("=== TEST UPDATE BED ===");

        dao.updateBedStatus("Bed_001", "Đang sử dụng");
        dao.updateBedStatus("Bed_999", "Đang sử dụng");
    }
}
//phan 1
//executeUpdate() trả về số dòng bị ảnh hưởng
//= 0 → không tồn tại
//
//0 → cập nhật thành công
