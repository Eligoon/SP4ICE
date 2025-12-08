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



    // Need to truncate the tables that needs a full, reset, such as Player and Inventory
    // Other tables needs to be updated, such as NPC's booleans so they are no longer dead
    /*
    public void deleteSave()
    {
        String sql = "TRUNCATE TABLE <Player>";

        Statement stmt = connection.createStatement();
    }



     */
}
