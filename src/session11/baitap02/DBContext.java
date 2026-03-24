package session11.baitap02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void colseConnection(Connection conn){
        try {
            if (conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}