package session11.baitap05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection()) {

            String sql = "SELECT * FROM Doctors";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Thêm bác sĩ (PreparedStatement)
    public void addDoctor(Doctor d) {

        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialization());

            ps.executeUpdate();
            System.out.println(" Thêm thành công");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Trùng mã bác sĩ!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. GROUP BY
    public void countBySpecialization() {

        String sql = "SELECT specialization, COUNT(*) as total FROM Doctors GROUP BY specialization";

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("specialization") + " - " + rs.getInt("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}