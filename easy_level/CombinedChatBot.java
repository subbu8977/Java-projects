// import java.awt.Desktop;
// import java.io.IOException;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.util.Scanner;

// public class ChatBot {

//     public static void main(String[] args) {
//         ChatBot bot = new ChatBot();
//         bot.start(); // Start the chatbot
//     }

//     public void start() {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Hello! How can I assist you today?");

//         while (true) {
//             String input = scanner.nextLine();
//             processCommand(input); // Process user command
//         }
//     }

//     private void processCommand(String command) {
//         if (command.equalsIgnoreCase("open browser")) {
//             openBrowser(); // Open browser
//         } else if (command.startsWith("search")) {
//             String query = command.substring(7);
//             searchWeb(query); // Search the web
//         } else {
//             System.out.println("Sorry, I don't understand that command."); // Handle unrecognized commands
//         }
//     }

//     private void openBrowser() {
//         try {
//             Desktop.getDesktop().browse(new URI("http://www.google.com")); // Open default browser to Google homepage
//         } catch (IOException | URISyntaxException e) {
//             e.printStackTrace(); // Handle exceptions
//         }
//     }

//     private void searchWeb(String query) {
//         try {
//             Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query)); // Open browser with search query
//         } catch (IOException | URISyntaxException e) {
//             e.printStackTrace(); // Handle exceptions
//         }
//     }
// }


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class CombinedChatBot {

    public static void main(String[] args) {
        CombinedChatBot bot = new CombinedChatBot();
        bot.start();  // Start the chatbot
    }

    // Method to start the chatbot's interaction with the user
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm your virtual assistant. How can I help you today?");

        // Continuously listen for user input in an infinite loop
        while (true) {
            String input = scanner.nextLine();  // Read the user's input
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye! Have a great day!");  // Exit the chatbot loop
                break;
            } else {
                processCommand(input);  // Process the user's input
            }
        }
    }

    // Method to process user commands or conversations
    private void processCommand(String command) {
        if (command.equalsIgnoreCase("open browser")) {
            openBrowser();  // Open the default web browser
        } else if (command.startsWith("search")) {
            String query = command.substring(7);  // Extract the search query
            searchWeb(query);  // Perform a web search
        } else {
            respond(command);  // If not a command, treat it as part of the conversation
        }
    }

    // Method to provide conversation-based responses
    private void respond(String input) {
        if (input.contains("hi") || input.contains("hello")) {
            System.out.println("Hello! How are you doing today?");
        } else if (input.contains("how are you")) {
            System.out.println("I'm just a computer program, but I'm functioning perfectly! How about you?");
        } else if (input.contains("what is your name")) {
            System.out.println("I'm your personal assistant. You can call me ChatBot.");
        } else if (input.contains("time")) {
            System.out.println("I can't tell you the time right now, but you can check your device's clock!");
        } else if (input.contains("joke")) {
            System.out.println("Why don't programmers like nature? It has too many bugs!");
        } else {
            System.out.println("Sorry, I don't have an answer for that. Could you ask something else?");
        }
    }

    // Method to open the default web browser to the Google homepage
    private void openBrowser() {
        try {
            Desktop.getDesktop().browse(new URI("http://www.google.com"));  // Open browser to Google homepage
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();  // Handle exceptions
        }
    }

    // Method to open the default web browser and perform a Google search with the user's query
    private void searchWeb(String query) {
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));  // Perform a web search
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();  // Handle exceptions
        }
    }
}
