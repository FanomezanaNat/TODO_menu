package todo;

import java.sql.Timestamp;
import java.util.List;

public interface TodoDAO {
    void addTodo(String title, String description, Timestamp deadline, int priority, boolean done);
    List<Todo> findTodo(String searchTodo);
    List<Todo> getTodos();
    boolean updateTodo(String todoToUpdate, String newTitle, String newDescription, Timestamp newDeadline, int newPriority, Boolean newDoneValue);
    boolean deleteTodo(String todo);
}
