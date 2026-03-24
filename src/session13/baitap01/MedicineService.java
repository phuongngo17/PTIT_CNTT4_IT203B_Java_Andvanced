package session13.baitap01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineService {
    public void dispenseMedicine(Connection conn, int medicineId, int patientId) throws SQLException {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            // Tắt auto-commit để chủ động quản lý transaction
            conn.setAutoCommit(false);

            // 1. Trừ thuốc trong kho
            String sql1 = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            // 2. Lưu lịch sử cấp phát
            String sql2 = "INSERT INTO Prescription_History(patient_id, medicine_id, date) VALUES (?, ?, ?)";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps2.executeUpdate();

            // Nếu cả hai thành công thì commit
            conn.commit();

        } catch (Exception e) {
            // Nếu có lỗi thì rollback toàn bộ
            if (conn != null) {
                conn.rollback();
            }
            throw new SQLException("Transaction failed, rolled back.", e);
        } finally {
            if (ps1 != null) ps1.close();
            if (ps2 != null) ps2.close();
            if (conn != null) conn.setAutoCommit(true); // bật lại auto-commit để tránh ảnh hưởng về sau
        }
    }
}
