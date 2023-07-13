package todo.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

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
            "INSERT INTO todo (title, description, deadline, priority, done) VALUES (?, ?, ?, ?, ?)");
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
    public List<Todo> findTodo(String searchTodo) {
        List<Todo> todo = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, deadline, priority, done FROM todo WHERE title ILIKE ?;");
            statement.setString(1, "%" + searchTodo + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");
               
                Todo todos = new Todo(title, description, deadline, priority, done);
                todo.add(todos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todo = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title, description, deadline, priority, done FROM todo");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");
                
                Todo todos = new Todo(title, description, deadline, priority, done);
                todo.add(todos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean updateTodo(String todoToUpdate, String NewTitle, String NewDescription, Timestamp NewDeadline, int NewPriority, Boolean NewDoneValue) {
        try {
            String query = "UPDATE todo SET title = ?, description = ? , deadline = ? , priority = ?, done = ? WHERE title ILIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
    
            statement.setString(1, NewTitle);
            statement.setString(2, NewDescription);
            statement.setTimestamp(3, NewDeadline);
            statement.setInt(4, NewPriority);
            statement.setBoolean(5, NewDoneValue);
            statement.setString(6, "%" + todoToUpdate + "%");
    
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM todo WHERE title ILIKE ?");
            statement.setString(1, "%" +todo+ "%");
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if any rows were deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}