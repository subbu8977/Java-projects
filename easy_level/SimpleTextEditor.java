import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SimpleTextEditor extends JFrame implements ActionListener {

    // Declare text area, scroll pane, menu bar, file menu, and menu items
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, closeItem;

    // Constructor to create the user interface
    public SimpleTextEditor() {
        createUI(); // Initialize the User Interface
    }

    // Method to set up the user interface
    private void createUI() {
        setTitle("Simple Text Editor"); // Set the title of the window

        textArea = new JTextArea(); // Create a text area
        scrollPane = new JScrollPane(textArea); // Add scroll pane to the text area
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of the frame

        menuBar = new JMenuBar(); // Create a menu bar
        fileMenu = new JMenu("File"); // Create a menu named "File"
        openItem = new JMenuItem("Open"); // Create menu item "Open"
        saveItem = new JMenuItem("Save"); // Create menu item "Save"
        closeItem = new JMenuItem("Close"); // Create menu item "Close"

        // Add action listeners for menu items
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);

        // Add menu items to the file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        menuBar.add(fileMenu); // Add file menu to the menu bar
        setJMenuBar(menuBar); // Set the menu bar for this frame

        setSize(500, 500); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setVisible(true); // Make the window visible
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleTextEditor::new); // Start the application
    }

    // Handle action events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile(); // Open file if "Open" is selected
        } else if (e.getSource() == saveItem) {
            saveFile(); // Save file if "Save" is selected
        } else if (e.getSource() == closeItem) {
            dispose(); // Close the application if "Close" is selected
        }
    }

    // Method to open a file
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser(); // Create a file chooser
        int option = fileChooser.showOpenDialog(this); // Show open dialog
        if (option == JFileChooser.APPROVE_OPTION) { // If the user approves the file selection
            File file = fileChooser.getSelectedFile(); // Get the selected file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null); // Read the file and display its content in the text area
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle exceptions
            }
        }
    }

    // Method to save a file
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser(); // Create a file chooser
        int option = fileChooser.showSaveDialog(this); // Show save dialog
        if (option == JFileChooser.APPROVE_OPTION) { // If the user approves the file save
            File file = fileChooser.getSelectedFile(); // Get the selected file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer); // Write the text area content to the file
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle exceptions
            }
        }
    }

    // Getter method to retrieve text area content
    public JTextArea getTextArea() {
        return textArea; // Return the text area
    }
}
