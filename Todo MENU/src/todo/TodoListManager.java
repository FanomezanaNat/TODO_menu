package todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoListManager {
    private static ArrayList<String> todos = new ArrayList<>();

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
        System.out.print("Enter the new todo: ");
        String todo = scanner.nextLine();
        todos.add(todo);
        System.out.println("Todo added successfully!");
    }

    public static void findTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo to search for: ");
        String searchTodo = scanner.nextLine();
        boolean found = false;

        for (String todo : todos) {
            if (todo.equalsIgnoreCase(searchTodo)) {
                System.out.println("Todo found: " + todo);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Todo not found.");
        }
    }

    public static void showTodos() {
        System.out.println("=== Todos ===");
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
        System.out.print("Enter the todo to update: ");
        String oldTodo = scanner.nextLine();
        System.out.print("Enter the new todo: ");
        String newTodo = scanner.nextLine();

        int index = todos.indexOf(oldTodo);
        if (index != -1) {
            todos.set(index, newTodo);
            System.out.println("Todo updated successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }


    public static void deleteTodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the todo to delete: ");
        String todo = scanner.nextLine();
        boolean removed = todos.remove(todo);

        if (removed) {
            System.out.println("Todo deleted successfully!");
        } else {
            System.out.println("Todo not found.");
        }
    }
}
