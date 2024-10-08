package hardlevel;

import java.util.ArrayList;           // Import ArrayList for product and cart management
import java.util.HashMap;             // Import HashMap for user account management
import java.util.Scanner;             // Import Scanner for user input

// Class representing a product
class Product {
    private String name;              // Product name
    private double price;             // Product price
    private String description;       // Product description
    private int stock;                // Available stock

    // Constructor to initialize product details
    public Product(String name, double price, String description, int stock) {
        this.name = name;              // Set product name
        this.price = price;            // Set product price
        this.description = description; // Set product description
        this.stock = stock;            // Set available stock
    }

    // Getters for product details
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    // Method to reduce stock when a product is purchased
    public void reduceStock(int quantity) {
        stock -= quantity;              // Decrease stock by purchased quantity
    }
}

// Class representing a shopping cart
class Cart {
    private ArrayList<Product> items; // List of products in the cart

    // Constructor to initialize cart
    public Cart() {
        items = new ArrayList<>();     // Create a new ArrayList for items
    }

    // Method to add a product to the cart
    public void addProduct(Product product) {
        items.add(product);             // Add product to the cart
    }

    // Method to display all products in the cart
    public void displayCart() {
        System.out.println("Your Cart:");
        for (Product product : items) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")"); // Display product details
        }
    }

    // Method to calculate total cost of items in the cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : items) {
            total += product.getPrice(); // Sum up prices
        }
        return total;                   // Return total amount
    }
}

// Class representing a user account
class User {
    private String username;          // User's username
    private String password;          // User's password

    // Constructor to initialize user account
    public User(String username, String password) {
        this.username = username;      // Set username
        this.password = password;      // Set password
    }

    // Getters for username and password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Class representing the e-commerce platform
public class ECommercePlatform {
    private static HashMap<String, User> users; // User accounts storage
    private static ArrayList<Product> products;   // List of available products
    private static Cart cart;                     // User's shopping cart
    private static Scanner scanner;               // Scanner for user input

    // Main method to run the e-commerce application
    public static void main(String[] args) {
        scanner = new Scanner(System.in); // Initialize scanner for user input
        users = new HashMap<>();           // Initialize user accounts
        products = new ArrayList<>();      // Initialize product list
        cart = new Cart();                 // Initialize shopping cart

        // Sample product data
        products.add(new Product("Laptop", 799.99, "High-performance laptop", 10));
        products.add(new Product("Smartphone", 499.99, "Latest smartphone model", 15));
        products.add(new Product("Headphones", 199.99, "Noise-cancelling headphones", 20));

        boolean running = true;

        while (running) {
            System.out.println("Welcome to the E-commerce Platform!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Browse Products");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");

            String choice = scanner.nextLine(); // Read user choice

            switch (choice) {
                case "1":
                    register(); // Call registration method
                    break;
                case "2":
                    login(); // Call login method
                    break;
                case "3":
                    browseProducts(); // Call product browsing method
                    break;
                case "4":
                    checkout(); // Call checkout method
                    break;
                case "5":
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Error message
            }
        }
    }

    // Method for user registration
    private static void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine(); // Read username
        System.out.println("Enter password: ");
        String password = scanner.nextLine(); // Read password
        users.put(username, new User(username, password)); // Store user in HashMap
        System.out.println("Registration successful!"); // Success message
    }

    // Method for user login
    private static void login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine(); // Read username
        System.out.println("Enter password: ");
        String password = scanner.nextLine(); // Read password

        // Validate user credentials
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("Login successful!"); // Success message
        } else {
            System.out.println("Invalid username or password. Please try again."); // Error message
        }
    }

    // Method to browse available products
    private static void browseProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")"); // Display product details
        }

        System.out.println("Enter product name to add to cart or type 'exit' to go back: ");
        String choice = scanner.nextLine(); // Read user choice
        boolean found = false;

        // Add chosen product to cart
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(choice)) {
                cart.addProduct(product); // Add product to cart
                product.reduceStock(1); // Reduce stock
                System.out.println("Added " + product.getName() + " to cart."); // Confirmation message
                found = true;
                break;
            }
        }

        if (!found && !choice.equalsIgnoreCase("exit")) {
            System.out.println("Product not found."); // Error message
        }
    }

    // Method to handle checkout process
    private static void checkout() {
        System.out.println("Proceeding to Checkout...");
        cart.displayCart(); // Show items in the cart
        double total = cart.calculateTotal(); // Calculate total price
        System.out.println("Total Amount: $" + total); // Display total amount
        System.out.println("Thank you for your purchase!"); // Purchase confirmation
        cart = new Cart(); // Reset cart after checkout
    }
}
