package session13.baitap02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Payment {
    public void payHospitalFee(Connection conn, int patientId, int invoiceId, double amount) throws SQLException {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            conn.setAutoCommit(false);

            // 1. Trừ tiền trong ví bệnh nhân
            String sql1 = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            // 2. Cập nhật trạng thái hóa đơn
            String sql2 = "UPDATE Invoices SET status = 'Đã thanh toán' WHERE invoice_id = ?";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();

            // Nếu cả hai thành công
            conn.commit();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Bắt buộc phải rollback khi có lỗi
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new SQLException("Transaction failed, rolled back.", e);
        } finally {
            if (ps1 != null) ps1.close();
            if (ps2 != null) ps2.close();
            if (conn != null) conn.setAutoCommit(true); // bật lại auto-commit
        }
    }

}
