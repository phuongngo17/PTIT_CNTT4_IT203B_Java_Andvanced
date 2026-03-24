package session11.baitap02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicineDao {
    public void printAllMedicine(){
        Connection conn = null;

        try{
            conn = DBContext.getConnection();
            String sql = "Select name, quatity";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBContext.colseConnection(conn);
        }
    }

}