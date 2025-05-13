package org.example.rulettjavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
        public static void updateUserCoins(int userId, int newCoinValue) {
            String sql = "UPDATE game SET coin_value = ? WHERE user_id = ?";

            try (Connection conn = DatabaseConnector.connect();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                System.out.println("Frissítés: " + userId + " -> " + newCoinValue);
                stmt.setInt(1, newCoinValue);
                stmt.setInt(2, userId);
                int rowsUpdated = stmt.executeUpdate();
                System.out.println("Sorok frissítve: " + rowsUpdated);
            } catch (SQLException e) {
                System.out.println("Hiba történt az adatbázis frissítésekor: " + e.getMessage());
                e.printStackTrace();
            }
        }
}
