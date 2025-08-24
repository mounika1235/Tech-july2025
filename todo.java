public class ToDoItem {
    private int id;
    private String title;
    private String description;
    private String status;

    public ToDoItem(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = "Pending";
    }
    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    private ArrayList<ToDoItem> items = new ArrayList<>();
    private int nextId = 1;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("0. Add To-Do Item");
            System.out.println("1. List All To-Do Items");
            System.out.println("2. View To-Do Item Details");
            System.out.println("3. Update To-Do Item");
            System.out.println("4. Delete To-Do Item");
            System.out.println("5. Update To-Do Item Status");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0: addItem(); break;
                case 1: listItems(); break;
                case 2: viewItem(); break;
                case 3: updateItem(); break;
                case 4: deleteItem(); break;
                case 5: updateStatus(); break;
                case 6: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addItem() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        ToDoItem item = new ToDoItem(nextId++, title, desc);
        items.add(item);
        System.out.println("Item added.");
    }

    private void listItems() {
        if (items.isEmpty()) {
            System.out.println("No items.");
            return;
        }
        for (ToDoItem item : items) {
            System.out.println(item.getId() + ": " + item.getTitle() + " [" + item.getStatus() + "]");
        }
    }

    private void viewItem() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ToDoItem item = findById(id);
        if (item != null) item.displayDetails();
        else System.out.println("Item not found.");
    }

    private void updateItem() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ToDoItem item = findById(id);
        if (item != null) {
            System.out.print("Enter new title: ");
            item.setTitle(scanner.nextLine());
            System.out.print("Enter new description: ");
            item.setDescription(scanner.nextLine());
            System.out.println("Item updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void deleteItem() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ToDoItem item = findById(id);
        if (item != null) {
            items.remove(item);
            System.out.println("Item deleted.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void updateStatus() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ToDoItem item = findById(id);
        if (item != null) {
            System.out.print("Enter new status (Pending/In Progress/Done): ");
            item.setStatus(scanner.nextLine());
            System.out.println("Status updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private ToDoItem findById(int id) {
        for (ToDoItem item : items) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}
 
public class Main {
    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.start();
    }
}

