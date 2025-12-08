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

    // Need to truncate the tables that needs a full, reset, such as Player and Inventory
    // Other tables needs to be updated, such as NPC's booleans so they are no longer dead
    /*
    public void deleteSave()
    {
        String sql = "TRUNCATE TABLE <Player>";

        Statement stmt = connection.createStatement();
    }




    // Potentially needed need to make columns
    public void addPlayer(columns) {
        String sql = "INSERT INTO Player (columns) VALUES ()";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     */
}
