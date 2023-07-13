package todo;

import java.sql.Timestamp;
import java.util.List;

public interface TodoDAO {
    void addTodo(String title, String description, Timestamp deadline, int priority, boolean done);
    List<Todo> findTodo(String searchTodo);
    List<Todo> getTodos();
    boolean updateTodo(Boolean done, String newTodo);
    boolean deleteTodo(String todo);
}