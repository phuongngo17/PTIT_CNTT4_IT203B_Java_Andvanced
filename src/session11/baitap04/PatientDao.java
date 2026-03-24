package session11.baitap04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDao {
    public String sanitizeInput(String input) {
        if (input == null) return "";

        return input.replaceAll("['\";]|--", "");    }

    public void searchPatient(String patientName) {
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();

            Statement stmt = conn.createStatement();

            patientName = sanitizeInput(patientName);

            String sql = "SELECT * FROM patients WHERE name = '" + patientName + "'";
            ResultSet rs = stmt.executeQuery(sql);

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("Tên: " + rs.getString("name"));
            }

            if (!found) {
                System.out.println(" Không tìm thấy bệnh nhân");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(conn);
        }
    }
}