package session14.bth;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String fromId = "ACC01";
        String toId = "ACC02";
        double amount = 1000;
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            // bật transaction
            conn.setAutoCommit(false);

            // 1. kiểm tra tài khoản và số dư
            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            double balance = 0;

            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, fromId);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new Exception("Tài khoản không tồn tại");
                }

                balance = rs.getDouble("Balance");

                if (balance < amount) {
                    throw new Exception("Không đủ tiền");
                }
            }

            // 2. gọi procedure trừ tiền
            String call = "{CALL sp_UpdateBalance(?, ?)}";

            try (CallableStatement cs = conn.prepareCall(call)) {
                cs.setString(1, fromId);
                cs.setDouble(2, -amount);
                cs.execute();
            }

            // 3. gọi procedure cộng tiền
            try (CallableStatement cs = conn.prepareCall(call)) {
                cs.setString(1, toId);
                cs.setDouble(2, amount);
                cs.execute();
            }

            // 4. commit
            conn.commit();
            System.out.println("Chuyển tiền thành công!");

            // 5. hiển thị kết quả
            String resultSql = "SELECT * FROM Accounts WHERE AccountId IN (?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(resultSql)) {
                ps.setString(1, fromId);
                ps.setString(2, toId);

                ResultSet rs = ps.executeQuery();

                System.out.println("===== KẾT QUẢ =====");
                while (rs.next()) {
                    System.out.println(
                            rs.getString("AccountId") + " | " +
                                    rs.getString("FullName") + " | " +
                                    rs.getDouble("Balance")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());

            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Đã rollback");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}