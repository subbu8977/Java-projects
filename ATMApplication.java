import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class for ATM Application
public class ATMApplication extends JFrame implements ActionListener {
    // GUI Components
    private JTextField accountField, amountField, transferField;
    private JTextArea outputArea;
    private JButton queryButton, withdrawButton, depositButton, transferButton;

    // Constructor
    public ATMApplication() {
        // Setting up the frame
        setTitle("ATM Machine");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Account input
        add(new JLabel("Account Number:"));
        accountField = new JTextField(15);
        add(accountField);

        // Amount input
        add(new JLabel("Amount:"));
        amountField = new JTextField(15);
        add(amountField);

        // Transfer input
        add(new JLabel("Transfer To:"));
        transferField = new JTextField(15);
        add(transferField);

        // Output area for results
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // Adding buttons
        queryButton = new JButton("Query Balance");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");

        add(queryButton);
        add(withdrawButton);
        add(depositButton);
        add(transferButton);

        // Registering action listeners
        queryButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        transferButton.addActionListener(this);

        // Make the frame visible
        setVisible(true);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String accountNumber = accountField.getText();
        String amountText = amountField.getText();
        String transferTo = transferField.getText();
        
        // Handle actions based on button clicked
        switch (command) {
            case "Query Balance":
                // Mock balance query
                outputArea.append("Balance for Account " + accountNumber + ": $1000\n");
                break;
            case "Withdraw":
                // Mock withdrawal
                outputArea.append("Withdrew $" + amountText + " from Account " + accountNumber + "\n");
                break;
            case "Deposit":
                // Mock deposit
                outputArea.append("Deposited $" + amountText + " to Account " + accountNumber + "\n");
                break;
            case "Transfer":
                // Mock transfer
                outputArea.append("Transferred $" + amountText + " from Account " + accountNumber + " to " + transferTo + "\n");
                break;
        }
        
        // Clear input fields after each action
        accountField.setText("");
        amountField.setText("");
        transferField.setText("");
    }

    // Main method to run the application
    public static void main(String[] args) {
        new ATMApplication();
    }
}
