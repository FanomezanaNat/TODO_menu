package todo.backend;

import java.sql.Timestamp;

public class Todo {
    private int id;
    private String title;
    private String description;
    private Timestamp deadline;
    private int priority;
    private boolean done;
    
    // Constructor for Type Todo:
    public Todo(int id, String title, String description, Timestamp deadline, int priority, boolean done) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
        this.id = id;
    }
    
    //Constructor without id:
    public Todo(String title, String description, Timestamp deadline, int priority, boolean done) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
    }
    
    // Getter and Setter:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "---------------------------------\n"
        + "\t. title: " + title +"\n"
        + "\t. description: " + description +"\n"
*        + "\t. Deadline: " + deadline +"\n"
        + "\t. Priority Level: " + priority +"\n"
        + "\t. is done: " +  done +"\n"
        ;
    }
}
