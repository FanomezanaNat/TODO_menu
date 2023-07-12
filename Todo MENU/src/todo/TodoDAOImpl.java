package todo;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoDAOImpl implements TodoDAO {
    private Connection connection;
    private DatabaseConnectionManager connectionManager;

    public TodoDAOImpl() {
        connectionManager = new DatabaseConnectionManager();
        connection = connectionManager.getConnection();
    }


    @Override
    public void addTodo(String title, String description, Timestamp deadline, int priority, boolean done) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO todo (title, description, deadline, priority, done) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setTimestamp(3, deadline);
            statement.setInt(4, priority);
            statement.setBoolean(5, done);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean findTodo(String searchTodo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM todo WHERE title = ?");
            statement.setString(1, searchTodo);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching todo is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getTodos() {
        List<String> todo = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title, deadline, priority, done FROM todo");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Date deadline = resultSet.getTimestamp("deadline");
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");
                todo.add(title);
                todo.add(String.valueOf(deadline));
                todo.add(String.valueOf(priority));
                todo.add((String.valueOf(done)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean updateTodo(Boolean done, String newTodo) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE todo SET done = ? WHERE title = ?");
            statement.setBoolean(1, done);
            statement.setString(2, newTodo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if any rows were updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTodo(String todo) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM todo WHERE title = ?");
            statement.setString(1, todo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if any rows were deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


