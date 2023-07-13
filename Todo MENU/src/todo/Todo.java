package todo;

import java.sql.Timestamp;

public class Todo {
    private String title;
    private String description;
    private Timestamp deadline;
    private int priority;
    private boolean done;
    
   

    // Contructor for Type Todo:
    public Todo(String title, String description, Timestamp deadline, int priority, boolean done) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
    }
    
    // Getter and Setter:
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
        + "\t. " + title +"\n"
        + "\t. " + description +"\n"
        + "\t. \n"
        + "\t. \n"
        + "\t---Status:\n"
        + "\t. Deadline: " + deadline +"\n"
        + "\t. Priority Level: " + priority +"\n"
        + "\t. is done: " +  done +"\n"
        ;
    }
}