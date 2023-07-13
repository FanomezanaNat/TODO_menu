package todo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class TodoListManager {
    private static TodoDAO todoDAO = new TodoDAOImpl();

    public static void displayMenu() {
        System.out.println("=== TODO Menu ===");
        System.out.println("1. Add aint new todo");
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
        if (priority > 10 || priority < 0) {
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
        System.out.print("Enter the todo's title to search for: ");
        String searchTodo = scanner.nextLine();
        List<Todo> todoTofind = todoDAO.findTodo(searchTodo);

        if (todoTofind.isEmpty()) {
            System.out.println(" => No todos found!" +
                    "\n---------------------------------");
        } else {
            System.out.println("\n => ALL FOUNDED TASKS: ");
            System.out.println(todoTofind.get(0));
            System.out.println("---------------------------------\n");
        }
    }

    public static void showTodos() {
        System.out.println("=== Todos ===");
        List<Todo> todos = todoDAO.getTodos();
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            System.out.println("\n => ALL FOUNDED TASKS: ");
            for (Todo todo : todos) {
                System.out.println(todo);
            }
            System.out.println("---------------------------------\n");
        }
    }

    public static void updateTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo's title to update: ");
        String todoToUpdate = scanner.nextLine();

        // Find Todos matching your search criteria
        List<Todo> foundTodos = todoDAO.findTodo(todoToUpdate);

        // Verify if any todos have been found
        if (foundTodos.isEmpty()) {
            System.out.println("No matching Todos found.");
            return;
        }

        // Display the found results
        System.out.println("Matching Todos found:");
        for (int i = 0; i < foundTodos.size(); i++) {
            Todo todo = foundTodos.get(i);
            int displayIndex = i + 1;
            System.out.println("[" + displayIndex + "] " + todo);
        }

        // Select a todo to update by index
        System.out.print("Enter the index of the Todo to update: ");
        int todoIndex = scanner.nextInt();
        scanner.nextLine(); // Clear the remaining line

        // Check if the index is valid
        if (todoIndex < 1 || todoIndex > foundTodos.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Todo selectedTodo = foundTodos.get(todoIndex - 1);

        // Ask for new values
        System.out.print("Enter the new title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter the new description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Enter the new deadline (yyyy-MM-dd HH:mm:ss): ");
        String deadlineString = scanner.nextLine();
        Timestamp newDeadline = Timestamp.valueOf(deadlineString);

        System.out.print("Enter the new priority: ");
        int newPriority = scanner.nextInt();

        System.out.print("Enter the new done value (true/false): ");
        boolean newDoneValue = scanner.nextBoolean();

        // Call the updateTodo() method with the new values
        boolean updated = todoDAO.updateTodo(selectedTodo.getTitle(), newTitle, newDescription, newDeadline,
                newPriority, newDoneValue);
        if (updated) {
            System.out.println("Todo updated successfully!");
        } else {
            System.out.println("Todo update failed.");
        }
    }

    public static void deleteTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo to delete: ");
        String todo = scanner.nextLine();

        // Search for Todos matching the search criteria
        List<Todo> foundTodos = todoDAO.findTodo(todo);

        // Check if any Todos were found
        if (foundTodos.isEmpty()) {
            System.out.println("No matching Todos found.");
            return;
        }

        // Display the found results with indices starting from 1
        System.out.println("Matching Todos found:");
        for (int i = 0; i < foundTodos.size(); i++) {
            Todo todoss = foundTodos.get(i);
            int displayIndex = i + 1;
            System.out.println("[" + displayIndex + "] " + todoss);
        }

        System.out.print("Enter the index of the Todo to delete: ");
        int todoIndex = scanner.nextInt();
        scanner.nextLine(); // Clear the remaining line

        // Check if the index is valid
        if (todoIndex < 1 || todoIndex > foundTodos.size()) {
            System.out.println("Invalid index.");
            return;
        }

        // Select the Todo to delete
        Todo selectedTodo = foundTodos.get(todoIndex - 1);

        // Delete the selected Todo
        boolean removed = todoDAO.deleteTodo(selectedTodo.getTitle());

        if (removed) {
            System.out.println("Todo deleted successfully!");
        } else {
            System.out.println("Todo deletion failed.");
        }
    }
}