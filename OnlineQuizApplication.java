import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Class representing a quiz question
class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

// Main class for the Quiz Application
public class OnlineQuizApplication extends JFrame implements ActionListener {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JTextArea resultArea;

    // Constructor to set up the quiz application
    public OnlineQuizApplication() {
        // Setting up the frame
        setTitle("Online Quiz Application");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initializing questions
        initializeQuestions();

        // Setting up question display
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        // Setting up options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Setting up next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        // Setting up result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Load the first question
        loadQuestion();

        // Make the frame visible
        setVisible(true);
    }

    // Method to initialize quiz questions
    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new Question("What is 2 + 2?",
                new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));
    }

    // Method to load the current question
    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.question);
            String[] options = currentQuestion.options;
            for (int i = 0; i < optionButtons.length; i++) {
                if (i < options.length) {
                    optionButtons[i].setText(options[i]);
                    optionButtons[i].setSelected(false);
                } else {
                    optionButtons[i].setVisible(false); // Hide unused buttons
                }
            }
        } else {
            showResult();
        }
    }

    // Method to display the result after the quiz is finished
    private void showResult() {
        resultArea.setText("Quiz Finished! Your score is: " + score + "/" + questions.size());
        nextButton.setEnabled(false);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (optionsGroup.getSelection() != null) {
            int selectedAnswer = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedAnswer = i;
                    break;
                }
            }

            // Check answer and update score
            if (selectedAnswer == questions.get(currentQuestionIndex).correctAnswer) {
                score++;
            }
            currentQuestionIndex++;
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new OnlineQuizApplication();
    }
}

