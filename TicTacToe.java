import javax.swing.*; // Import Swing components
import java.awt.*; // Import AWT components
import java.awt.event.*; // Import event handling classes

public class TicTacToe extends JFrame implements ActionListener { // Extend JFrame and implement ActionListener
    private JButton[][] buttons = new JButton[3][3]; // 2D array of buttons for the board
    private char currentPlayer; // Variable to keep track of the current player ('X' or 'O')
    private JLabel statusLabel; // Label to display the current game status

    public TicTacToe() { // Constructor to set up the game
        currentPlayer = 'X'; // Set the initial player to 'X'
        setTitle("Tic-Tac-Toe"); // Set the title of the window
        setSize(400, 400); // Set the size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit the application on close
        setLayout(new BorderLayout()); // Set layout manager to BorderLayout

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new GridLayout(3, 3)); // Set grid layout for 3x3 buttons

        // Initialize buttons and add action listeners
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(); // Create a new button
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60)); // Set font size
                buttons[row][col].setFocusPainted(false); // Remove focus outline
                buttons[row][col].addActionListener(this); // Add action listener to each button
                buttonPanel.add(buttons[row][col]); // Add button to the panel
            }
        }

        // Create a status label and add it to the top of the window
        statusLabel = new JLabel("Current Player: " + currentPlayer); 
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label
        add(statusLabel, BorderLayout.NORTH); // Add label to the top

        add(buttonPanel, BorderLayout.CENTER); // Add button panel to the center
        setVisible(true); // Make the window visible
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Handle button click events
        JButton clickedButton = (JButton) e.getSource(); // Get the clicked button
        if (clickedButton.getText().equals("")) { // Check if the button is empty
            clickedButton.setText(String.valueOf(currentPlayer)); // Set the text to the current player
            if (checkForWin()) { // Check if the current player has won
                statusLabel.setText("Player " + currentPlayer + " wins!"); // Update status label
                disableButtons(); // Disable all buttons
            } else if (isBoardFull()) { // Check for a draw
                statusLabel.setText("It's a draw!"); // Update status label
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
                statusLabel.setText("Current Player: " + currentPlayer); // Update status label
            }
        }
    }

    private boolean checkForWin() { // Check if there is a winner
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(String.valueOf(currentPlayer)) && 
                 buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[i][2].getText().equals(String.valueOf(currentPlayer))) || 
                (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) && 
                 buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[2][i].getText().equals(String.valueOf(currentPlayer)))) {
                return true; // Return true if a row or column has the same player's symbol
            }
        }
        // Check diagonals
        if ((buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
             buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
             buttons[2][2].getText().equals(String.valueOf(currentPlayer))) || 
            (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
             buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
             buttons[2][0].getText().equals(String.valueOf(currentPlayer)))) {
            return true; // Return true if a diagonal has the same player's symbol
        }
        return false; // Return false if there is no winner
    }

    private boolean isBoardFull() { // Check if the board is full
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) { // If there is an empty button
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full
    }

    private void disableButtons() { // Disable all buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false); // Disable each button
            }
        }
    }

    public static void main(String[] args) { // Main method to start the game
        new TicTacToe(); // Create an instance of the TicTacToe class
    }
}
