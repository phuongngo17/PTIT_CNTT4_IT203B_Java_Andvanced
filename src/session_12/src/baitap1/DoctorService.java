package baitap1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorService {

    public static boolean login(String code, String pass) {
        String sql = "SELECT * FROM doctors WHERE code = ? AND pass = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, code);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công!");
                System.out.println("Xin chào: " + rs.getString("full_name"));
                System.out.println("Quyền: " + rs.getString("role"));
                return true;
            } else {
                System.out.println("Sai mã bác sĩ hoặc mật khẩu.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi kết nối hoặc truy vấn database:");
            e.printStackTrace();
            return false;
        }
    }
}