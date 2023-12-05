import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGuess {
    private static int number;
    private static int attemptsLeft;
    private static int score;
    private static int roundCounter;

    private static JLabel attemptsLabel;
    private static JLabel scoreLabel;
    private static JLabel roundLabel;

    public static void main(String[] args) {
        JFrame f = new JFrame("Number Guessing Game");
        JButton generateButton = new JButton("Generate a Random Number");
        JButton submitButton = new JButton("Submit");
        JTextField guessTextField = new JTextField("Guess the number");
        JLabel infoLabel = new JLabel("A Random Number is generated! Try to guess it in fewer attempts!!");
        JLabel resultLabel = new JLabel("");
        attemptsLabel = new JLabel("");
        scoreLabel = new JLabel("Score: 0");
        roundLabel = new JLabel("Round: 0");
		JLabel l1 = new JLabel( "Congratulations!You guessed the number.");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.add(generateButton);
        panel.add(guessTextField);
        panel.add(submitButton);
        panel.add(infoLabel);
        panel.add(resultLabel);
        panel.add(attemptsLabel);
        panel.add(scoreLabel);
        panel.add(roundLabel);

        // Set preferred sizes for components
        int preferredWidth = 200;
        int preferredHeight = 30;
        Dimension preferredSize = new Dimension(preferredWidth, preferredHeight);
        generateButton.setPreferredSize(preferredSize);
        guessTextField.setPreferredSize(preferredSize);
        submitButton.setPreferredSize(preferredSize);
        infoLabel.setPreferredSize(preferredSize);
        resultLabel.setPreferredSize(preferredSize);
        attemptsLabel.setPreferredSize(preferredSize);
        scoreLabel.setPreferredSize(preferredSize);
        roundLabel.setPreferredSize(preferredSize);

        f.add(panel);
		score = 0;

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = 1 + (int) (100 * Math.random());
				System.out.println(number);
                attemptsLeft = 5;
                
                roundCounter++;
                infoLabel.setText("A Random Number is generated! Try to guess it in fewer attempts!!");
                guessTextField.setText("");
                resultLabel.setText("");
                updateAttemptsLabel();
                updateScoreLabel();
                updateRoundLabel();
            }
        });

        
				 submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessTextField.getText());

                    if (attemptsLeft > 0) {
                        attemptsLeft--;

                        if (guess == number) {
                            f.add(l1);
                            score += attemptsLeft + 1; // Award points based on remaining attempts
                            updateScoreLabel();
                            generateButton.doClick(); // Start a new round
                        } else if (guess < number) {
                            resultLabel.setText("Too low. Try again!");
                        } else {
                            resultLabel.setText("Too high. Try again!");
                        }

                        updateAttemptsLabel();
                    } else {
                        resultLabel.setText("Out of attempts. The correct number was " + number + ".");
                        generateButton.doClick(); // Start a new round
                    }
                } catch (NumberFormatException ex) {
                    infoLabel.setText("Please enter a valid number.");
                }
            }
        });


                    
        f.setSize(400, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null); // Center the frame on the screen
        f.setVisible(true);
    }

    private static void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    private static void updateScoreLabel() {
		
        scoreLabel.setText("Score: " + score);
    }

    private static void updateRoundLabel() {
        roundLabel.setText("Round: " + roundCounter);
    }
}
