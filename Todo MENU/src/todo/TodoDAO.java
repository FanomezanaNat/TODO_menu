package todo;

import java.sql.Timestamp;
import java.util.List;

public interface TodoDAO {
    void addTodo(String title, String description, Timestamp deadline, int priority, boolean done);
    boolean findTodo(String searchTodo);
    List<String> getTodos();
    boolean updateTodo(Boolean done, String newTodo);
    boolean deleteTodo(String todo);


}
