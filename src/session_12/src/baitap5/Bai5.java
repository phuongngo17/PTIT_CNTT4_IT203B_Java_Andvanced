package baitap5;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class Bai5 {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hospital_bai5?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "30063008";

    public static void main(String[] args) {
        try {
            initializeDatabase();
        } catch (SQLException e) {
            System.out.println("Khong the khoi tao database.");
            e.printStackTrace();
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                int choice = readInt(scanner, "Chon chuc nang: ");

                switch (choice) {
                    case 1:
                        listPatients();
                        break;
                    case 2:
                        admitPatient(scanner);
                        break;
                    case 3:
                        updateDiagnosis(scanner);
                        break;
                    case 4:
                        dischargeAndCalculateFee(scanner);
                        break;
                    case 5:
                        System.out.println("Da thoat chuong trinh.");
                        return;
                    default:
                        System.out.println("Lua chon khong hop le. Vui long chon 1-5.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== QUAN LY BENH NHAN NOI TRU =====");
        System.out.println("1. Danh sach benh nhan");
        System.out.println("2. Tiep nhan benh nhan moi");
        System.out.println("3. Cap nhat benh an");
        System.out.println("4. Xuat vien va tinh phi");
        System.out.println("5. Thoat");
    }

    private static void listPatients() {
        String sql = "SELECT patient_id, patient_code, full_name, age, department " +
                "FROM inpatients WHERE discharged = 0 ORDER BY patient_id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- DANH SACH BENH NHAN NOI TRU -----");
            System.out.printf("%-6s %-12s %-25s %-6s %-20s%n", "ID", "Ma BN", "Ten", "Tuoi", "Khoa");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf(
                        "%-6d %-12s %-25s %-6d %-20s%n",
                        rs.getInt("patient_id"),
                        rs.getString("patient_code"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("department")
                );
            }

            if (!hasData) {
                System.out.println("Chua co benh nhan noi tru.");
            }
        } catch (SQLException e) {
            System.out.println("Loi khi lay danh sach benh nhan.");
            e.printStackTrace();
        }
    }

    private static void admitPatient(Scanner scanner) {
        String sql = "INSERT INTO inpatients(patient_code, full_name, age, department, diagnosis, admit_date, discharged) " +
                "VALUES (?, ?, ?, ?, ?, CURDATE(), 0)";

        String patientCode = readNonEmpty(scanner, "Nhap ma benh nhan: ");
        String fullName = readNonEmpty(scanner, "Nhap ten benh nhan: ");
        int age = readInt(scanner, "Nhap tuoi: ");
        String department = readNonEmpty(scanner, "Nhap khoa dieu tri: ");
        String diagnosis = readNonEmpty(scanner, "Nhap benh ly ban dau: ");

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, patientCode);
            ps.setString(2, fullName);
            ps.setInt(3, age);
            ps.setString(4, department);
            ps.setString(5, diagnosis);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Tiep nhan benh nhan thanh cong.");
            }
        } catch (SQLException e) {
            System.out.println("Loi khi tiep nhan benh nhan.");
            e.printStackTrace();
        }
    }

    private static void updateDiagnosis(Scanner scanner) {
        String sql = "UPDATE inpatients SET diagnosis = ? WHERE patient_id = ? AND discharged = 0";

        int patientId = readInt(scanner, "Nhap ID benh nhan can cap nhat: ");
        String newDiagnosis = readNonEmpty(scanner, "Nhap chan doan moi: ");

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newDiagnosis);
            ps.setInt(2, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Cap nhat benh an thanh cong.");
            } else {
                System.out.println("Khong tim thay benh nhan noi tru voi ID nay.");
            }
        } catch (SQLException e) {
            System.out.println("Loi khi cap nhat benh an.");
            e.printStackTrace();
        }
    }

    private static void dischargeAndCalculateFee(Scanner scanner) {
        int patientId = readInt(scanner, "Nhap ID benh nhan xuat vien: ");

        String checkSql = "SELECT full_name FROM inpatients WHERE patient_id = ? AND discharged = 0";
        String updateSql = "UPDATE inpatients SET discharged = 1, discharge_date = CURDATE(), discharge_fee = ? " +
                "WHERE patient_id = ? AND discharged = 0";

        try (Connection conn = getConnection()) {
            String patientName = null;
            try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setInt(1, patientId);
                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next()) {
                        patientName = rs.getString("full_name");
                    }
                }
            }

            if (patientName == null) {
                System.out.println("Khong tim thay benh nhan noi tru voi ID nay.");
                return;
            }

            conn.setAutoCommit(false);
            try {
                BigDecimal fee = callCalculateFee(conn, patientId);

                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                    updatePs.setBigDecimal(1, fee);
                    updatePs.setInt(2, patientId);
                    updatePs.executeUpdate();
                }

                conn.commit();
                System.out.println("Xuat vien thanh cong cho benh nhan: " + patientName);
                System.out.println("Tong vien phi: " + fee + " VND");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Loi khi xuat vien va tinh phi.");
            e.printStackTrace();
        }
    }

    private static BigDecimal callCalculateFee(Connection conn, int patientId) throws SQLException {
        String sql = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, patientId);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();

            BigDecimal fee = cs.getBigDecimal(2);
            return fee != null ? fee : BigDecimal.ZERO;
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = readConfig("DB_URL", "db.url", DEFAULT_URL);
        String user = readConfig("DB_USER", "db.user", DEFAULT_USER);
        String password = readConfig("DB_PASSWORD", "db.password", DEFAULT_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }

    private static String readConfig(String envKey, String propKey, String defaultValue) {
        String env = System.getenv(envKey);
        if (env != null && !env.trim().isEmpty()) {
            return env.trim();
        }
        String prop = System.getProperty(propKey);
        if (prop != null && !prop.trim().isEmpty()) {
            return prop.trim();
        }
        return defaultValue;
    }

    private static void initializeDatabase() throws SQLException {
        String url = readConfig("DB_URL", "db.url", DEFAULT_URL);
        String user = readConfig("DB_USER", "db.user", DEFAULT_USER);
        String password = readConfig("DB_PASSWORD", "db.password", DEFAULT_PASSWORD);

        String dbName = extractDatabaseName(url);
        if (dbName == null || dbName.isEmpty()) {
            throw new SQLException("DB_URL khong chua ten database hop le.");
        }

        String serverUrl = toServerUrl(url);
        try (Connection conn = DriverManager.getConnection(serverUrl, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE IF NOT EXISTS `" + dbName + "`");
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS inpatients (" +
                    "patient_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "patient_code VARCHAR(20) NOT NULL UNIQUE," +
                    "full_name VARCHAR(120) NOT NULL," +
                    "age INT NOT NULL CHECK (age >= 0)," +
                    "department VARCHAR(100) NOT NULL," +
                    "diagnosis TEXT NOT NULL," +
                    "admit_date DATE NOT NULL," +
                    "discharge_date DATE NULL," +
                    "discharge_fee DECIMAL(15,2) NULL," +
                    "discharged TINYINT(1) NOT NULL DEFAULT 0" +
                    ")";
            stmt.execute(createTable);
            stmt.execute("DROP PROCEDURE IF EXISTS CALCULATE_DISCHARGE_FEE");
            stmt.execute(buildCreateProcedureSql());
        }
    }

    private static String buildCreateProcedureSql() {
        return "CREATE PROCEDURE CALCULATE_DISCHARGE_FEE(IN p_patient_id INT, OUT total_fee DECIMAL(15,2)) " +
                "BEGIN " +
                "DECLARE v_days INT DEFAULT 0; " +
                "DECLARE v_age INT DEFAULT 0; " +
                "DECLARE v_department VARCHAR(100); " +
                "DECLARE v_daily_rate DECIMAL(15,2) DEFAULT 700000; " +
                "SELECT age, department, GREATEST(DATEDIFF(CURDATE(), admit_date) + 1, 1) " +
                "INTO v_age, v_department, v_days " +
                "FROM inpatients WHERE patient_id = p_patient_id AND discharged = 0 LIMIT 1; " +
                "IF v_department IS NULL THEN " +
                "SET total_fee = 0; " +
                "ELSE " +
                "IF v_department = 'ICU' THEN SET v_daily_rate = 1200000; " +
                "ELSEIF v_department = 'Cardiology' THEN SET v_daily_rate = 900000; " +
                "ELSEIF v_department = 'Neurology' THEN SET v_daily_rate = 950000; " +
                "ELSE SET v_daily_rate = 700000; " +
                "END IF; " +
                "SET total_fee = (v_days * v_daily_rate) + " +
                "(CASE WHEN v_age >= 65 THEN 300000 ELSE 0 END); " +
                "END IF; " +
                "END";
    }

    private static String extractDatabaseName(String url) {
        String prefix = "jdbc:mysql://";
        if (!url.startsWith(prefix)) {
            return null;
        }

        int slashPos = url.indexOf('/', prefix.length());
        if (slashPos < 0 || slashPos + 1 >= url.length()) {
            return null;
        }

        int questionPos = url.indexOf('?', slashPos + 1);
        if (questionPos < 0) {
            return url.substring(slashPos + 1).trim();
        }
        return url.substring(slashPos + 1, questionPos).trim();
    }

    private static String toServerUrl(String url) {
        String prefix = "jdbc:mysql://";
        int slashPos = url.indexOf('/', prefix.length());
        if (slashPos < 0) {
            return url;
        }
        int questionPos = url.indexOf('?', slashPos + 1);
        String base = url.substring(0, slashPos + 1);
        String query = questionPos > -1 ? url.substring(questionPos) : "";
        return base + query;
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le, vui long nhap so nguyen.");
            }
        }
    }

    private static String readNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Khong duoc de trong.");
        }
    }
}