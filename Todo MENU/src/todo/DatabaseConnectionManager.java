package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static todo.Settings.*;

public class DatabaseConnectionManager {
    private String url;
    private String user;
    private String password;


    public Connection getConnection() {
        this.url = "jdbc:postgresql://localhost/" + DATABASE;
        this.user = USERNAME;
        this.password = PASSWORD;
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
