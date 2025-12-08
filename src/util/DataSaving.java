package util;
import java.sql.*;

public class DataSaving {

    Connection connection;

    public void connect(String url) {
        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
