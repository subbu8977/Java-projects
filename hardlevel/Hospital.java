package hardlevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a user in the social media platform
class User {
    private String username;                              // User's username
    private String password;                              // User's password
    private List<Post> posts;                             // List of posts created by the user
    private boolean isPrivate;                            // User's privacy setting

    // Constructor to initialize user attributes
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();                   // Initialize the posts list
        this.isPrivate = false;                           // Default profile is public
    }

    // Getters for user attributes
    public String getUsername() {
        return username;                                   // Return the username
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);             // Check if the password matches
    }

    public List<Post> getPosts() {
        return posts;                                      // Return the list of user's posts
    }

    public boolean isPrivate() {
        return isPrivate;                                  // Return the privacy setting
    }

    public void togglePrivacy() {
        isPrivate = !isPrivate;                            // Toggle the privacy setting
    }

    public void createPost(String content) {
        posts.add(new Post(this, content));                // Create a new post and add it to the user's posts
    }
}

// Class representing a post created by a user
class Post {
    private static int postCount = 0;                    // Static counter for unique post IDs
    private int postId;                                   // Unique ID for each post
    private User author;                                  // Author of the post
    private String content;                               // Content of the post
    private List<Comment> comments;                       // List of comments on the post
    private int likes;                                    // Count of likes on the post

    // Constructor to initialize post attributes
    public Post(User author, String content) {
        this.postId = ++postCount;                       // Increment and assign unique ID
        this.author = author;
        this.content = content;
        this.comments = new ArrayList<>();                // Initialize the comments list
        this.likes = 0;                                   // Initialize the likes count
    }

    // Method to add a comment to the post
    public void addComment(Comment comment) {
        comments.add(comment);                             // Add a comment to the list
    }

    // Method to like the post
    public void like() {
        likes++;                                          // Increment the likes count
    }

    // Getters for post attributes
    public int getPostId() {
        return postId;                                    // Return the post ID
    }

    public String getContent() {
        return content;                                   // Return the post content
    }

    public List<Comment> getComments() {
        return comments;                                   // Return the list of comments
    }

    public int getLikes() {
        return likes;                                     // Return the number of likes
    }

    public User getAuthor() {
        return author;                                    // Return the post author
    }
}

// Class representing a comment on a post
class Comment {
    private User commenter;                               // User who made the comment
    private String content;                               // Content of the comment

    // Constructor to initialize comment attributes
    public Comment(User commenter, String content) {
        this.commenter = commenter;
        this.content = content;                           // Set the content of the comment
    }

    // Getters for comment attributes
    public User getCommenter() {
        return commenter;                                 // Return the commenter
    }

    public String getContent() {
        return content;                                  // Return the content of the comment
    }
}

// Main class for the social media platform
public class Hospital {
    private static List<User> users;                     // List of registered users
    private static Scanner scanner;                       // Scanner for user input

    // Main method to run the social media platform
    public static void main(String[] args) {
        scanner = new Scanner(System.in);                 // Initialize scanner
        users = new ArrayList<>();                         // Initialize users list

        boolean running = true;                            // Flag to keep the application running

        // Main application loop
        while (running) {
            System.out.println("Welcome to the Social Media Platform!");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();            // Read user choice

            switch (choice) {
                case "1":
                    registerUser();                         // Call user registration method
                    break;
                case "2":
                    loginUser();                            // Call user login method
                    break;
                case "3":
                    running = false;                        // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Invalid input handling
            }
        }
    }

    // Method for user registration
    private static void registerUser() {
        System.out.println("Enter username: ");            // Prompt for username
        String username = scanner.nextLine();              // Read username
        System.out.println("Enter password: ");            // Prompt for password
        String password = scanner.nextLine();              // Read password

        // Check for duplicate username
        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists. Please choose another one.");
            return;                                        // Exit the method
        }

        users.add(new User(username, password));            // Create new user and add to the list
        System.out.println("User registered successfully!"); // Confirmation message
    }

    // Method for user login
    private static void loginUser() {
        System.out.println("Enter username: ");            // Prompt for username
        String username = scanner.nextLine();              // Read username
        System.out.println("Enter password: ");            // Prompt for password
        String password = scanner.nextLine();              // Read password

        // Find user by username
        User user = findUserByUsername(username);           // Call method to find user
        if (user != null && user.checkPassword(password)) { // Check if password matches
            System.out.println("Login successful!");        // Confirmation message
            userMenu(user);                                 // Proceed to user menu
        } else {
            System.out.println("Invalid username or password."); // Error message
        }
    }

    // Method for user menu after successful login
    private static void userMenu(User user) {
        boolean loggedIn = true;                            // Flag to keep user menu running
        while (loggedIn) {
            // Display user menu options
            System.out.println("1. Create Post");
            System.out.println("2. View Posts");
            System.out.println("3. Toggle Privacy");
            System.out.println("4. Logout");

            String choice = scanner.nextLine();            // Read user choice

            switch (choice) {
                case "1":
                    createPost(user);                        // Call method to create post
                    break;
                case "2":
                    viewPosts(user);                         // Call method to view posts
                    break;
                case "3":
                    user.togglePrivacy();                    // Toggle privacy settings
                    System.out.println("Privacy setting changed to: " + (user.isPrivate() ? "Private" : "Public")); // Confirmation message
                    break;
                case "4":
                    loggedIn = false;                        // Exit the user menu
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Invalid input handling
            }
        }
    }

    // Method for creating a post
    private static void createPost(User user) {
        System.out.println("Enter content for the post: "); // Prompt for post content
        String content = scanner.nextLine();                 // Read post content

        user.createPost(content);                             // Create new post and add to the user's profile
        System.out.println("Post created successfully!");     // Confirmation message
    }

    // Method for viewing all posts
    private static void viewPosts(User user) {
        System.out.println("Posts by " + user.getUsername() + ":"); // Display posts header
        for (Post post : user.getPosts()) {                      // Iterate through user's posts
            System.out.println("Post ID: " + post.getPostId() + " - " + post.getContent() + " (Likes: " + post.getLikes() + ")");
            // Display each post's content and like count

            // Display comments on the post
            for (Comment comment : post.getComments()) {         // Iterate through comments on the post
                System.out.println("   Comment by " + comment.getCommenter().getUsername() + ": " + comment.getContent());
                // Display each comment's content
            }
            System.out.println();                                  // New line for separation
        }
    }

    // Method to find a user by username
    private static User findUserByUsername(String username) {
        for (User user : users) {                                // Iterate through the list of users
            if (user.getUsername().equals(username)) {          // Check for matching username
                return user;                                     // Return found user
            }
        }
        return null;                                            // Return null if user not found
    }
}

