package util;
import java.sql.*;

public class DataSaving {
    // Connection to database
    Connection connection;

    // Method to establish connection to our database
    public void connect(String url) {
        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Creating tables for gameState, player and inventory if they do not already exist.
    // Should only run the very first time the game is run.
    private void createTables() {
        try (Statement stmt = connection.createStatement()) {

            String sqlGameState = """
                CREATE TABLE IF NOT EXISTS game_state (
                    id INTEGER PRIMARY KEY,
                    current_location VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlGameState);

            String sqlPlayer = """
                CREATE TABLE IF NOT EXISTS player (
                     id INTEGER PRIMARY KEY,
                     name VARCHAR(100),
                     race VARCHAR(50),
                     class VARCHAR(50),
                     health INTEGER,
                     strength INTEGER,
                     dexterity INTEGER,
                     intelligence INTEGER,
                     location VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlPlayer);

            String sqlInventory = """
                CREATE TABLE IF NOT EXISTS inventory (
                    player_id INTEGER,
                    item_name VARCHAR(100)
                );
                """;
            stmt.executeUpdate(sqlInventory);

        } catch (SQLException e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }

    // Note to self, look into INSERT and REPLACE
    // TRUNCATE is not supported in SQLite

    // SaveGameState

    // SaveCurrentLocation

    // SavePlayer

    // SaveInventory

    // LoadGameState

    // LoadLocation

    // LoadPlayer

    // LoadInventory

    // Delete rows in the tables to prepare for a new game
    public void deleteSave() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM game_state;");
            stmt.executeUpdate("DELETE FROM player;");
            stmt.executeUpdate("DELETE FROM inventory;");
            System.out.println("Save deleted.");
        } catch (SQLException e) {
            System.err.println("Could not delete save:");
            e.printStackTrace();
        }
    }
}
