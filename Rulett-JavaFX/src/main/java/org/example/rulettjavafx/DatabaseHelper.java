package org.example.rulettjavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    public static void updateUserCoins(int userId, int newCoinValue) {
        String updateSql = "UPDATE game SET coin_value = ? WHERE user_id = ?";
        String insertSql = "INSERT INTO game (user_id, coin_value, chosen_color, result_color, won) " +
                "VALUES (?, ?, 'white', 'white', 0)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            updateStmt.setInt(1, newCoinValue);
            updateStmt.setInt(2, userId);

            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated == 0) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, newCoinValue);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while updating the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getUserCoins(int userId) {
        String querySql = "SELECT coin_value FROM game WHERE user_id = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement queryStmt = conn.prepareStatement(querySql)) {

            queryStmt.setInt(1, userId);
            ResultSet resultSet = queryStmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("coin_value");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching user coins: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static void logGameResult(int userId, String chosenColor, String resultColor, boolean won) {
        String insertSql = "INSERT INTO game_log (user_id, chosen_color, result_color, won) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            insertStmt.setInt(1, userId);
            insertStmt.setString(2, chosenColor);
            insertStmt.setString(3, resultColor);
            insertStmt.setBoolean(4, won);
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error occurred while logging game result: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
