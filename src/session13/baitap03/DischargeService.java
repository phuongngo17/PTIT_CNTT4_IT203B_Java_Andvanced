package session13.baitap03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DischargeService {

    public void xuatVienVaThanhToan(Connection conn, int maBenhNhan, double tienVienPhi) throws SQLException {
        PreparedStatement psCheck = null;
        PreparedStatement psUpdateWallet = null;
        PreparedStatement psUpdateBed = null;
        PreparedStatement psUpdatePatient = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            // 1. Lấy số dư tạm ứng
            String sqlCheck = "SELECT balance, bed_id FROM Patient_Wallet WHERE patient_id = ?";
            psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, maBenhNhan);
            rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Bẫy 2: Không tìm thấy bệnh nhân với mã " + maBenhNhan);
            }

            double balance = rs.getDouble("balance");
            int bedId = rs.getInt("bed_id");

            // Bẫy 1: Kiểm tra số dư
            if (balance < tienVienPhi) {
                throw new SQLException("Bẫy 1: Số dư không đủ để thanh toán viện phí!");
            }

            // 2. Trừ tiền viện phí
            String sqlWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            psUpdateWallet = conn.prepareStatement(sqlWallet);
            psUpdateWallet.setDouble(1, tienVienPhi);
            psUpdateWallet.setInt(2, maBenhNhan);
            int rowsWallet = psUpdateWallet.executeUpdate();
            if (rowsWallet == 0) {
                throw new SQLException("Bẫy 2: Không cập nhật được số dư bệnh nhân!");
            }

            // 3. Giải phóng giường bệnh
            String sqlBed = "UPDATE Beds SET status = 'Trống' WHERE bed_id = ?";
            psUpdateBed = conn.prepareStatement(sqlBed);
            psUpdateBed.setInt(1, bedId);
            int rowsBed = psUpdateBed.executeUpdate();
            if (rowsBed == 0) {
                throw new SQLException("Bẫy 2: Không cập nhật được trạng thái giường!");
            }

            // 4. Cập nhật trạng thái bệnh nhân
            String sqlPatient = "UPDATE Patients SET status = 'Đã xuất viện' WHERE patient_id = ?";
            psUpdatePatient = conn.prepareStatement(sqlPatient);
            psUpdatePatient.setInt(1, maBenhNhan);
            int rowsPatient = psUpdatePatient.executeUpdate();
            if (rowsPatient == 0) {
                throw new SQLException("Bẫy 2: Không cập nhật được trạng thái bệnh nhân!");
            }

            // Nếu tất cả thành công
            conn.commit();
            System.out.println("Xuất viện & thanh toán thành công cho bệnh nhân " + maBenhNhan);

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new SQLException("Transaction thất bại, đã rollback.", e);
        } finally {
            if (rs != null) rs.close();
            if (psCheck != null) psCheck.close();
            if (psUpdateWallet != null) psUpdateWallet.close();
            if (psUpdateBed != null) psUpdateBed.close();
            if (psUpdatePatient != null) psUpdatePatient.close();
            if (conn != null) conn.setAutoCommit(true);
        }
    }
}

