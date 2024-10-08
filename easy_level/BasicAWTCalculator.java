import java.awt.*;               // AWT package for GUI components
import java.awt.event.*;         // AWT event package for handling user actions

// Class for a basic calculator using AWT
public class BasicAWTCalculator extends Frame implements ActionListener {
    // Declare TextFields for user input and result display
    private TextField input1, input2, resultField;
    
    // Declare Labels to identify the input and result fields
    private Label label1, label2, label3;
    
    // Declare Buttons for operations: addition, subtraction, multiplication, division, and clear
    private Button addButton, subButton, mulButton, divButton, clearButton;

    // Constructor to set up the GUI
    public BasicAWTCalculator() {
        // Set the title of the calculator window
        setTitle("AWT Basic Calculator");
        
        // Set the window size
        setSize(400, 300);
        
        // Use a GridLayout with 6 rows and 2 columns to organize the components
        setLayout(new GridLayout(6, 2));
        
        // Position the window in the center of the screen
        setLocationRelativeTo(null);
        
        // Disable resizing of the window
        setResizable(false);

        // Initialize labels for the input fields and result
        label1 = new Label("First Number:");
        label2 = new Label("Second Number:");
        label3 = new Label("Result:");

        // Initialize TextFields for the input fields and result display
        input1 = new TextField();     // First number input
        input2 = new TextField();     // Second number input
        resultField = new TextField();  // Display the result
        resultField.setEditable(false);  // Make the result field read-only

        // Initialize buttons for calculator operations
        addButton = new Button("+");    // Addition button
        subButton = new Button("-");    // Subtraction button
        mulButton = new Button("*");    // Multiplication button
        divButton = new Button("/");    // Division button
        clearButton = new Button("Clear");  // Clear button to reset inputs and result

        // Add components to the Frame (window)
        add(label1);     // Add label for first number
        add(input1);     // Add text field for first number
        add(label2);     // Add label for second number
        add(input2);     // Add text field for second number
        add(label3);     // Add label for result
        add(resultField);  // Add text field for result display (read-only)
        add(addButton);  // Add addition button
        add(subButton);  // Add subtraction button
        add(mulButton);  // Add multiplication button
        add(divButton);  // Add division button
        add(clearButton);  // Add clear button

        // Attach ActionListeners to the buttons to handle button clicks
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add a listener for window closing to exit the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);  // Exit the application when the window is closed
            }
        });
    }

    // This method is called when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the text input from both input fields
        String num1Text = input1.getText();
        String num2Text = input2.getText();
        try {
            // Convert the input text to double for calculations
            double num1 = Double.parseDouble(num1Text);
            double num2 = Double.parseDouble(num2Text);
            double result = 0;  // Variable to store the result

            // Check which button was clicked and perform the corresponding operation
            if (e.getSource() == addButton) {
                result = num1 + num2;  // Perform addition
            } else if (e.getSource() == subButton) {
                result = num1 - num2;  // Perform subtraction
            } else if (e.getSource() == mulButton) {
                result = num1 * num2;  // Perform multiplication
            } else if (e.getSource() == divButton) {
                // Handle division by zero
                if (num2 == 0) {
                    resultField.setText("Error: Div by 0");  // Display error message for division by zero
                    return;  // Exit the method without further execution
                }
                result = num1 / num2;  // Perform division
            } else if (e.getSource() == clearButton) {
                // Clear all text fields when the clear button is clicked
                input1.setText("");
                input2.setText("");
                resultField.setText("");
                return;  // Exit the method after clearing
            }

            // Display the result in the result field
            resultField.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            // Handle invalid input (e.g., if the user enters non-numeric text)
            resultField.setText("Error: Invalid input");
        }
    }

    // Main method to launch the calculator application
    public static void main(String[] args) {
        // Create an instance of the calculator
        BasicAWTCalculator calculator = new BasicAWTCalculator();
        
        // Make the calculator window visible
        calculator.setVisible(true);
    }
}
