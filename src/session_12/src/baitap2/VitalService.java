package baitap2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VitalService {

    public static void updateVitals(int patientId, double temperature, int heartRate) {
        String sql = "UPDATE vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setDouble(1, temperature); // double
            pstmt.setInt(2, heartRate);      // int
            pstmt.setInt(3, patientId);      // int

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi database:");
            e.printStackTrace();
        }
    }
}