package org.example.rulettjavafx;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/rulett";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
