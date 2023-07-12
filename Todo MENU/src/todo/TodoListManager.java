package todo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class TodoListManager {
    private static TodoDAO todoDAO = new TodoDAOImpl();

    public static void displayMenu() {
        System.out.println("=== TODO Menu ===");
        System.out.println("1. Add a new todo");
        System.out.println("2. Find a todo");
        System.out.println("3. Show todos");
        System.out.println("4. Update a todo");
        System.out.println("5. Delete a todo");
        System.out.println("6. Quit");
        System.out.print("Enter your choice: ");
    }

    public static void addTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the deadline (YYYY-MM-DD HH:MM:SS): ");
        String deadlineStr = scanner.nextLine();
        Timestamp deadline = Timestamp.valueOf(deadlineStr);
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        if (deadline.before(currentDate)) {
            System.out.println("The deadline cannot be in the past.");
            return; // Exit the method if the deadline is in the past
        }
        System.out.print("Enter the priority (0-10): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (priority>10 || priority<0){
            System.out.println("priority must be between 0-10");
            return;

        }
        System.out.print("Is it done? (true/false): ");
        boolean done = scanner.nextBoolean();
        scanner.nextLine(); // Consume the newline character

        todoDAO.addTodo(title, description, deadline, priority, done);
        System.out.println("Todo added successfully!");
    }

    public static void findTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo to search for: ");
        String searchTodo = scanner.nextLine();
        boolean found = todoDAO.findTodo(searchTodo);

        if (found) {
            System.out.println("Todo found: " + searchTodo);
        } else {
            System.out.println("Todo not found.");
        }
    }

    public static void showTodos() {
        System.out.println("=== Todos ===");
        List<String> todos = todoDAO.getTodos();
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            for (String todo : todos) {
                System.out.println(todo);
            }
        }
    }

    public static void updateTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new todo's state: ");
        String done = scanner.nextLine();
        System.out.print("Enter todo: ");
        String newTodo = scanner.nextLine();

        boolean updated = todoDAO.updateTodo(Boolean.valueOf(done), newTodo);
        if (updated) {
            System.out.println("Todo updated successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }

    public static void deleteTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo to delete: ");
        String todo = scanner.nextLine();
        boolean removed = todoDAO.deleteTodo(todo);

        if (removed) {
            System.out.println("Todo deleted successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }
}

