package session11.baitap01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientDao {

    public void getPatients() {
        String sql = "SELECT * FROM patients";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Disease: " + rs.getString("disease")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}