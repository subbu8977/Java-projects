import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Main class for Library Resource Administrator
public class LibraryResourceAdmin extends Frame implements ActionListener {
    // GUI Components
    private TextField volumeField, patronField, searchField;
    private TextArea outputArea;
    private Button addVolumeButton, addPatronButton, loanButton, repayButton, searchButton;
    
    // Constructor
    public LibraryResourceAdmin() {
        // Setting up the frame
        setTitle("Library Resource Administrator");
        setSize(500, 400);
        setLayout(new FlowLayout());
        
        // Volume input
        add(new Label("Volume Name:"));
        volumeField = new TextField(15);
        add(volumeField);
        
        // Patron input
        add(new Label("Patron Name:"));
        patronField = new TextField(15);
        add(patronField);
        
        // Search input
        add(new Label("Search Volume:"));
        searchField = new TextField(15);
        add(searchField);
        
        // Output area for results
        outputArea = new TextArea(10, 40);
        outputArea.setEditable(false);
        add(outputArea);
        
        // Adding buttons
        addVolumeButton = new Button("Add Volume");
        addPatronButton = new Button("Add Patron");
        loanButton = new Button("Loan");
        repayButton = new Button("Repay");
        searchButton = new Button("Search");
        
        add(addVolumeButton);
        add(addPatronButton);
        add(loanButton);
        add(repayButton);
        add(searchButton);
        
        // Registering action listeners
        addVolumeButton.addActionListener(this);
        addPatronButton.addActionListener(this);
        loanButton.addActionListener(this);
        repayButton.addActionListener(this);
        searchButton.addActionListener(this);
        
        // Window listener for closing the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        // Making the frame visible
        setVisible(true);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Add Volume":
                String volume = volumeField.getText();
                outputArea.append("Added Volume: " + volume + "\n");
                volumeField.setText(""); // Clear input field
                break;
            case "Add Patron":
                String patron = patronField.getText();
                outputArea.append("Added Patron: " + patron + "\n");
                patronField.setText(""); // Clear input field
                break;
            case "Loan":
                outputArea.append("Loaned Volume: " + volumeField.getText() + " to Patron: " + patronField.getText() + "\n");
                volumeField.setText(""); // Clear input field
                patronField.setText(""); // Clear input field
                break;
            case "Repay":
                outputArea.append("Repayment processed for: " + patronField.getText() + "\n");
                patronField.setText(""); // Clear input field
                break;
            case "Search":
                String searchQuery = searchField.getText();
                outputArea.append("Searching for Volume: " + searchQuery + "\n");
                searchField.setText(""); // Clear input field
                break;
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new LibraryResourceAdmin();
    }
}
