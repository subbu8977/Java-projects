import java.awt.*;                      // Import AWT package for GUI components
import java.awt.event.*;                // Import AWT event package for handling user actions
import java.io.*;                       // Import for file handling
import java.util.ArrayList;             // Import for using ArrayList to store user data

// Class representing the Registration Manager application
public class RegistrationManager extends Frame implements ActionListener {
    // Components for the registration form
    private TextField nameField;         // TextField for user name input
    private TextField emailField;        // TextField for user email input
    private TextField ageField;          // TextField for user age input
    private Checkbox maleCheckbox;       // Checkbox for male selection
    private Checkbox femaleCheckbox;     // Checkbox for female selection
    private Choice countryChoice;         // Combo box for selecting a country
    private TextArea displayArea;         // TextArea to display registered user information
    private Button registerButton;        // Button to register user
    private Button displayButton;         // Button to display user data
    private Button exportButton;          // Button to export user data to a .txt file

    // ArrayList to hold registered users
    private ArrayList<User> users;

    // Constructor to set up the GUI
    public RegistrationManager() {
        // Initialize the Frame (window)
        setTitle("Registration Manager");
        setSize(400, 400);
        setLayout(new FlowLayout());      // Set the layout to FlowLayout
        setLocationRelativeTo(null);      // Center the window
        setResizable(false);              // Disable resizing

        // Initialize components
        users = new ArrayList<>();        // Initialize the users list

        // Labels and input fields
        add(new Label("Name:"));
        nameField = new TextField(20);    // TextField for entering name
        add(nameField);

        add(new Label("Email:"));
        emailField = new TextField(20);   // TextField for entering email
        add(emailField);

        add(new Label("Age:"));
        ageField = new TextField(3);      // TextField for entering age
        add(ageField);

        // Gender selection using checkboxes
        maleCheckbox = new Checkbox("Male");
        femaleCheckbox = new Checkbox("Female");
        add(maleCheckbox);
        add(femaleCheckbox);

        // Country selection using combo box
        add(new Label("Country:"));
        countryChoice = new Choice();      // Create a choice (combo box) for countries
        countryChoice.add("Select Country"); // Default prompt
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("UK");
        countryChoice.add("Australia");
        add(countryChoice);

        // TextArea for displaying user data
        displayArea = new TextArea(10, 30); // TextArea for output, with specific size
        displayArea.setEditable(false);     // Make the display area read-only
        add(displayArea);

        // Initialize buttons
        registerButton = new Button("Register");
        displayButton = new Button("Display");
        exportButton = new Button("Export");

        // Add action listeners to buttons
        registerButton.addActionListener(this);
        displayButton.addActionListener(this);
        exportButton.addActionListener(this);

        // Add buttons to the frame
        add(registerButton);
        add(displayButton);
        add(exportButton);

        // Add window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);  // Close the application when the window is closed
            }
        });
    }

    // Method to handle button click actions
    @Override
    public void actionPerformed(ActionEvent e) {
        // Register button action
        if (e.getSource() == registerButton) {
            registerUser();  // Call method to register user
        }
        // Display button action
        else if (e.getSource() == displayButton) {
            displayUsers();  // Call method to display registered users
        }
        // Export button action
        else if (e.getSource() == exportButton) {
            exportUsers();   // Call method to export user data to a file
        }
    }

    // Method to register a user
    private void registerUser() {
        // Get input from fields
        String name = nameField.getText();
        String email = emailField.getText();
        String ageText = ageField.getText();
        String gender = maleCheckbox.getState() ? "Male" : femaleCheckbox.getState() ? "Female" : "Not Specified";
        String country = countryChoice.getSelectedItem();

        // Validate input
        if (name.isEmpty() || email.isEmpty() || ageText.isEmpty() || country.equals("Select Country")) {
            displayArea.setText("Please fill all fields properly.");
            return;  // Exit method if validation fails
        }

        // Convert age input to integer
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            displayArea.setText("Please enter a valid age.");
            return;  // Exit method if age conversion fails
        }

        // Create User object and add to users list
        users.add(new User(name, email, age, gender, country));
        displayArea.setText("User registered successfully!"); // Confirmation message
    }

    // Method to display registered users
    private void displayUsers() {
        // Clear the display area
        displayArea.setText("");
        // Loop through the users list and append user details
        for (User user : users) {
            displayArea.append(user.toString() + "\n"); // Append user details
        }
    }

    // Method to export user data to a .txt file
    private void exportUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("registered_users.txt"))) {
            // Write user data to file
            for (User user : users) {
                writer.println(user.toString());
            }
            displayArea.setText("User data exported successfully!"); // Confirmation message
        } catch (IOException ex) {
            displayArea.setText("Error exporting user data."); // Error handling
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        RegistrationManager app = new RegistrationManager(); // Create instance of RegistrationManager
        app.setVisible(true); // Make the window visible
    }
}

// Class to represent a User with attributes
class User {
    private String name;  // User's name
    private String email; // User's email
    private int age;      // User's age
    private String gender; // User's gender
    private String country; // User's country

    // Constructor to initialize User object
    public User(String name, String email, int age, String gender, String country) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    // Override toString method to return user details in a readable format
    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Age: " + age + ", Gender: " + gender + ", Country: " + country;
    }
}
