package baitap3;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Phan2 {
    public static void getSurgeryFee(Connection conn) {
        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            // truyền tham số đầu vào
            cstmt.setInt(1, 505);

            // đăng ký tham số đầu ra
            cstmt.registerOutParameter(2, Types.DECIMAL);

            // thực thi thủ tục
            cstmt.execute();

            // lấy giá trị trả về
            BigDecimal cost = cstmt.getBigDecimal(2);

            System.out.println("Chi phí phẫu thuật: " + cost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
