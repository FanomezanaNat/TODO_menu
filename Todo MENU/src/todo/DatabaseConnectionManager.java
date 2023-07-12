package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static todo.Settings.*;

public class DatabaseConnectionManager {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
