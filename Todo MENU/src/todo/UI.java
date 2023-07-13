package todo;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            TodoListManager.displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); //
            switch (choice) {
                case 1 -> TodoListManager.addTodo() ;
                case 2 -> TodoListManager.findTodo();
                case 3 -> TodoListManager.showTodos();
                case 4 -> TodoListManager.updateTodo();
                case 5 -> TodoListManager.deleteTodo();
                case 6 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}