import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;

        Random random = new Random();
        int generatedNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        JOptionPane.showMessageDialog(null, "Welcome to Guess the Number Game!\n" +
                "I have generated a number between " + minRange + " and " + maxRange +
                ".\nYou have " + maxAttempts + " attempts to guess the number.");

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            String input = JOptionPane.showInputDialog("Attempt " + attempts + ": Enter your guess:");
            int guess = Integer.parseInt(input);

            if (guess == generatedNumber) {
                score = maxAttempts - attempts + 1;
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number in " +
                        attempts + " attempt(s).\nYour score is: " + score);
                break;
            } else if (guess < generatedNumber) {
                JOptionPane.showMessageDialog(null, "Try again! The generated number is higher.");
            } else {
                JOptionPane.showMessageDialog(null, "Try again! The generated number is lower.");
            }
        }

        if (score == 0) {
            JOptionPane.showMessageDialog(null, "Game over! The generated number was: " + generatedNumber);
        }

        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            playGame();
        } else {
            JOptionPane.showMessageDialog(null, "Thank you for playing Guess the Number Game!");
        }
    }
}
