package session14.miniProject.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SRS Giai doan 2: dung {@link Statement} de doc va chay script DDL/khoi tao DB tu file
 * {@code schema_flash_sale.sql} (cac cau lenh tinh, khong tham so tu nguoi dung).
 * <p>
 * Luu y: Stored procedure / function trong MySQL co nhieu dau ';' trong than — khong tach
 * bang split(";") don gian. File SQL duoc to chuc thanh cac khoi tach boi dong {@code ---BLOCK---}.
 * <p>
 * Chay mot lan: {@code main} (can DB {@code session14_flash_sale} da ton tai, va JDBC URL dung).
 */
public final class Session14SchemaInitializer {

    private static final String SCRIPT_RESOURCE = "schema_flash_sale.sql";
    private static final String BLOCK_SEPARATOR = "---BLOCK---";

    private Session14SchemaInitializer() {
    }

    /**
     * Doc script tu classpath (cung package voi class config), tach khoi, thuc thi bang Statement.
     */
    public static void applySchema(DatabaseConnectionManager manager) throws SQLException, IOException {
        String sqlText = readScript();
        List<String> blocks = splitIntoExecutableBlocks(sqlText);
        try (Connection con = manager.getConnection()) {
            try (Statement st = con.createStatement()) {
                for (String block : blocks) {
                    String sql = block.trim();
                    if (sql.isEmpty()) {
                        continue;
                    }
                    st.execute(sql);
                }
            }
        }
    }

    static String readScript() throws IOException {
        try (InputStream in = Session14SchemaInitializer.class.getResourceAsStream(SCRIPT_RESOURCE)) {
            if (in == null) {
                throw new IOException("Khong doc duoc resource: " + SCRIPT_RESOURCE);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        }
    }

    /**
     * Tach script: bo comment hang '--' o dau dong (don gian), roi cat theo ---BLOCK---.
     * Moi khoi = mot lenh SQL hop le (CREATE TABLE, CREATE PROCEDURE ... END, CREATE FUNCTION ... END).
     */
    static List<String> splitIntoExecutableBlocks(String sqlText) {
        StringBuilder stripped = new StringBuilder();
        for (String line : sqlText.split("\n")) {
            String t = line.trim();
            if (t.startsWith("--") && !t.startsWith("---")) {
                continue;
            }
            stripped.append(line).append('\n');
        }
        String[] parts = stripped.toString().split(BLOCK_SEPARATOR);
        List<String> out = new ArrayList<>();
        for (String p : parts) {
            String s = p.trim();
            if (!s.isEmpty()) {
                out.add(s);
            }
        }
        return out;
    }

    /**
     * Vi du chay khoi tao: can MySQL, database session14_flash_sale da duoc tao truoc.
     */
    public static void main(String[] args) throws Exception {
        DatabaseConnectionManager mgr = DatabaseConnectionManager.getInstance();
        applySchema(mgr);
        System.out.println("Da ap dung schema_flash_sale.sql thanh cong.");
    }
}
