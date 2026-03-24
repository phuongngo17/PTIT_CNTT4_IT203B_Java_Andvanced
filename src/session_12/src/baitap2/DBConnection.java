package baitap2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DBConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hospital_db2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "30063008";

    public static Connection getConnection() throws SQLException {
        String url = readConfig("DB_URL", "db.url", DEFAULT_URL);
        String user = readConfig("DB_USER", "db.user", DEFAULT_USER);
        String password = readConfig("DB_PASSWORD", "db.password", DEFAULT_PASSWORD);

        try {
            ensureDatabaseInitialized(url, user, password);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLInvalidAuthorizationSpecException e) {
            throw new SQLException(
                    "Database authentication failed. Configure DB credentials via env vars (DB_URL, DB_USER, DB_PASSWORD) " +
                            "or JVM args (-Ddb.url=..., -Ddb.user=..., -Ddb.password=...).",
                    e
            );
        } catch (SQLException e) {
            if (e.getErrorCode() == 1049) {
                throw new SQLException(
                        "Database does not exist: " + extractDatabaseName(url) +
                                ". The application attempted to initialize it automatically but failed.",
                        e
                );
            }
            throw e;
        }
    }

    private static String readConfig(String envKey, String propKey, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }

        String propValue = System.getProperty(propKey);
        if (propValue != null && !propValue.trim().isEmpty()) {
            return propValue.trim();
        }

        return defaultValue;
    }

    private static void ensureDatabaseInitialized(String url, String user, String password) throws SQLException {
        String dbName = extractDatabaseName(url);
        if (dbName == null || dbName.isEmpty()) {
            return;
        }

        String serverUrl = toServerUrl(url);
        try (Connection conn = DriverManager.getConnection(serverUrl, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE IF NOT EXISTS `" + dbName + "`");
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            applySchemaIfPresent(conn);
        }
    }

    private static String extractDatabaseName(String url) {
        int prefix = "jdbc:mysql://".length();
        if (!url.startsWith("jdbc:mysql://") || url.length() <= prefix) {
            return null;
        }

        int slashPos = url.indexOf('/', prefix);
        if (slashPos < 0 || slashPos + 1 >= url.length()) {
            return null;
        }

        int questionPos = url.indexOf('?', slashPos + 1);
        String dbName = (questionPos > -1) ? url.substring(slashPos + 1, questionPos) : url.substring(slashPos + 1);
        return dbName.trim();
    }

    private static String toServerUrl(String url) {
        int prefix = "jdbc:mysql://".length();
        int slashPos = url.indexOf('/', prefix);
        if (slashPos < 0) {
            return url;
        }

        int questionPos = url.indexOf('?', slashPos + 1);
        String hostPort = url.substring(0, slashPos + 1);
        String query = (questionPos > -1) ? url.substring(questionPos) : "";
        return hostPort + query;
    }

    private static void applySchemaIfPresent(Connection conn) throws SQLException {
        String sqlScript = readSqlScript("hospital_db2.sql");
        if (sqlScript == null || sqlScript.trim().isEmpty()) {
            return;
        }

        String[] statements = sqlScript.split(";");
        try (Statement stmt = conn.createStatement()) {
            for (String raw : statements) {
                String sql = raw.trim();
                if (sql.isEmpty()) {
                    continue;
                }

                String upper = sql.toUpperCase();
                if (upper.startsWith("CREATE DATABASE") || upper.startsWith("USE ")) {
                    continue;
                }
                stmt.execute(sql);
            }
        }
    }

    private static String readSqlScript(String scriptName) {
        try (InputStream is = DBConnection.class.getResourceAsStream(scriptName)) {
            if (is == null) {
                return null;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            return null;
        }
    }
}