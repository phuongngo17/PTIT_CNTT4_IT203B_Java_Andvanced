package session11.baitap03;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class BedDao {

    public void updateBedStatus(String bedId, String status) {

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "UPDATE Bed SET bed_status = ? WHERE bed_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status);
            ps.setString(2, bedId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Cập nhật thành công! Số dòng: " + rowsAffected);
            } else {
                System.out.println(" Mã giường không tồn tại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}