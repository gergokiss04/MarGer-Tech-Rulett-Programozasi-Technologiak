package org.example.rulettjavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    public static void updateUserCoins(int userId, int newCoinValue) {
        String updateSql = "UPDATE game SET coin_value = ? WHERE user_id = ?";
        String insertSql = "INSERT INTO game (user_id, coin_value, chosen_color, result_color, won) " +
                "VALUES (?, ?, 'white', 'white', 0)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            System.out.println("Attempting to update user coins...");
            System.out.println("User ID: " + userId);
            System.out.println("New Coin Value: " + newCoinValue);

            updateStmt.setInt(1, newCoinValue);
            updateStmt.setInt(2, userId);

            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            if (rowsUpdated == 0) {
                System.out.println("No rows were updated. Attempting to insert a new row...");
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, newCoinValue);
                    int rowsInserted = insertStmt.executeUpdate();
                    System.out.println("Rows inserted: " + rowsInserted);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while updating the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
