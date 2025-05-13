package org.example.rulettjavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect() throws SQLException {
        System.out.println("Connecting to database...");
        System.out.println("URL: " + DatabaseConfig.getUrl());
        System.out.println("User: " + DatabaseConfig.getUser());

        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUser(),
                DatabaseConfig.getPassword()
        );
    }
}
