package baitap1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLException;

public class DBConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USER = "app_user";
    private static final String DEFAULT_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        String url = readConfig("DB_URL", "db.url", DEFAULT_URL);
        String user = readConfig("DB_USER", "db.user", DEFAULT_USER);
        String password = readConfig("DB_PASSWORD", "db.password", DEFAULT_PASSWORD);

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLInvalidAuthorizationSpecException e) {
            throw new SQLException(
                    "Database authentication failed. Configure DB credentials via env vars (DB_URL, DB_USER, DB_PASSWORD) " +
                            "or JVM args (-Ddb.url=..., -Ddb.user=..., -Ddb.password=...).",
                    e
            );
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
}