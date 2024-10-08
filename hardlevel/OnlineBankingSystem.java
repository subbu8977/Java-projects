package hardlevel;
import java.util.ArrayList;           // Import ArrayList for dynamic array usage
import java.util.Scanner;             // Import Scanner for user input

// Class representing a bank account
class Account {
    private String accountHolder;      // Account holder's name
    private double balance;             // Account balance
    private int accountNumber;          // Unique account number
    private ArrayList<String> transactionHistory; // List of transaction history
    private static int accountCounter = 1000; // Static counter to generate unique account numbers

    // Constructor to initialize the account
    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder; // Set the account holder's name
        this.balance = initialBalance;       // Set initial balance
        this.accountNumber = accountCounter++; // Assign a unique account number
        transactionHistory = new ArrayList<>(); // Initialize transaction history
        addTransaction("Initial deposit: $" + initialBalance); // Log initial deposit
    }

    // Method to view account balance
    public void viewBalance() {
        System.out.println("Account Balance: $" + balance); // Display balance
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) { // Check if deposit amount is positive
            balance += amount; // Update balance
            addTransaction("Deposit: $" + amount); // Log transaction
            System.out.println("Deposited: $" + amount); // Confirmation message
        } else {
            System.out.println("Deposit amount must be positive."); // Error message
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // Check if withdrawal amount is valid
            balance -= amount; // Update balance
            addTransaction("Withdraw: $" + amount); // Log transaction
            System.out.println("Withdrew: $" + amount); // Confirmation message
        } else {
            System.out.println("Insufficient funds or invalid amount."); // Error message
        }
    }

    // Method to view transaction history
    public void viewTransactionHistory() {
        System.out.println("Transaction History for " + accountHolder + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction); // Display each transaction
        }
    }

    // Method to transfer funds to another account
    public void transferFunds(Account recipient, double amount) {
        if (recipient != null && amount > 0 && amount <= balance) { // Check if transfer is valid
            this.withdraw(amount); // Withdraw from current account
            recipient.deposit(amount); // Deposit into recipient's account
            System.out.println("Transferred: $" + amount + " to " + recipient.getAccountHolder()); // Log transaction
        } else {
            System.out.println("Transfer failed: Invalid recipient or insufficient funds."); // Error message
        }
    }

    // Method to pay bills
    public void payBill(double amount) {
        if (amount > 0 && amount <= balance) { // Check if bill payment is valid
            balance -= amount; // Update balance
            addTransaction("Paid bill: $" + amount); // Log transaction
            System.out.println("Paid bill: $" + amount); // Confirmation message
        } else {
            System.out.println("Insufficient funds or invalid amount."); // Error message
        }
    }

    // Method to add a transaction to the history
    private void addTransaction(String transaction) {
        transactionHistory.add(transaction); // Add transaction to history
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter for account holder's name
    public String getAccountHolder() {
        return accountHolder;
    }
}

// Class representing a bank
class Bank {
    private ArrayList<Account> accounts; // List of bank accounts

    // Constructor to initialize the bank
    public Bank() {
        accounts = new ArrayList<>(); // Initialize accounts list
    }

    // Method to register a new account
    public void registerAccount(String name, double initialBalance) {
        Account newAccount = new Account(name, initialBalance); // Create new account
        accounts.add(newAccount); // Add account to list
        System.out.println("Account registered successfully! Your account number is: " + newAccount.getAccountNumber()); // Success message
    }

    // Method to get an account by account number
    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (accountNumber == account.getAccountNumber()) { // Match found
                return account; // Return the found account
            }
        }
        return null; // Return null if not found
    }

    // Method to transfer funds between accounts
    public void transferFunds(Account sender, int recipientAccountNumber, double amount) {
        Account recipient = getAccount(recipientAccountNumber); // Find recipient account
        sender.transferFunds(recipient, amount); // Transfer funds
    }
}

// Main class for the online banking system
public class OnlineBankingSystem {
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input
    private static Bank bank = new Bank(); // Instance of the bank

    // Main method to run the application
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to Online Banking System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String choice = scanner.nextLine(); // Read user choice

            switch (choice) {
                case "1":
                    register(); // Call register method
                    break;
                case "2":
                    login(); // Call login method
                    break;
                case "3":
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Error message
            }
        }
    }

    // Method for user registration
    private static void register() {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine(); // Read name
        System.out.println("Enter your initial balance: ");
        double balance = Double.parseDouble(scanner.nextLine()); // Read initial balance
        bank.registerAccount(name, balance); // Register account
    }

    // Method for user login
    private static void login() {
        System.out.println("Enter your account number: ");
        int accountNumber = Integer.parseInt(scanner.nextLine()); // Read account number
        Account account = bank.getAccount(accountNumber); // Retrieve account

        if (account != null) {
            boolean loggedIn = true;

            while (loggedIn) {
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Transaction History");
                System.out.println("5. Transfer Funds");
                System.out.println("6. Pay Bills");
                System.out.println("7. Logout");

                String choice = scanner.nextLine(); // Read user choice

                switch (choice) {
                    case "1":
                        account.viewBalance(); // View balance
                        break;
                    case "2":
                        System.out.println("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine()); // Read deposit amount
                        account.deposit(depositAmount); // Deposit amount
                        break;
                    case "3":
                        System.out.println("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine()); // Read withdrawal amount
                        account.withdraw(withdrawAmount); // Withdraw amount
                        break;
                    case "4":
                        account.viewTransactionHistory(); // View transaction history
                        break;
                    case "5":
                        System.out.println("Enter recipient's account number: ");
                        int recipientAccountNumber = Integer.parseInt(scanner.nextLine()); // Read recipient account number
                        System.out.println("Enter amount to transfer: ");
                        double transferAmount = Double.parseDouble(scanner.nextLine()); // Read transfer amount
                        bank.transferFunds(account, recipientAccountNumber, transferAmount); // Transfer funds
                        break;
                    case "6":
                        System.out.println("Enter bill amount: ");
                        double billAmount = Double.parseDouble(scanner.nextLine()); // Read bill amount
                        account.payBill(billAmount); // Pay bill
                        break;
                    case "7":
                        loggedIn = false; // Logout
                        break;
                    default:
                        System.out.println("Invalid option. Please try again."); // Error message
                }
            }
        } else {
            System.out.println("Account not found. Please try again."); // Error message
        }
    }
}
