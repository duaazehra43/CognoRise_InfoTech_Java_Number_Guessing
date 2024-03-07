import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private int randomNumber;
    private int attempts;
    private static final int MAX_ATTEMPTS = 5;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

    
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(240, 240, 240);
                Color color2 = new Color(200, 200, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        JLabel titleLabel = new JLabel("Welcome to the Number Guessing Game");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.setBackground(Color.GREEN);
        guessButton.setForeground(Color.WHITE);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.setBackground(Color.RED);
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        centerPanel.add(promptLabel);
        centerPanel.add(guessField);
        centerPanel.add(guessButton);
        centerPanel.add(restartButton); 
        backgroundPanel.add(centerPanel); 
        add(backgroundPanel, BorderLayout.CENTER);

        feedbackLabel = new JLabel();
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(feedbackLabel, BorderLayout.SOUTH);

        attemptsLabel = new JLabel("Attempts left: " + (MAX_ATTEMPTS - attempts));
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(attemptsLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == randomNumber) {
                feedbackLabel.setText("Congratulations! You guessed the number.");
                guessField.setEditable(false);
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low. Try again.");
            } else {
                feedbackLabel.setText("Too high. Try again.");
            }
            attempts++;
            attemptsLabel.setText("Attempts left: " + (MAX_ATTEMPTS - attempts));
            if (attempts >= MAX_ATTEMPTS) {
                feedbackLabel.setText("Sorry, you've reached the maximum number of attempts. The number was: " + randomNumber);
                guessField.setEditable(false);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        attemptsLabel.setText("Attempts left: " + (MAX_ATTEMPTS - attempts));
        feedbackLabel.setText("");
        guessField.setEditable(true);
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
