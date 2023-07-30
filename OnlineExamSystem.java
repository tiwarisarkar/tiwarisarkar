import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class OnlineExamSystem {
    private static Map<String, User> users;
    private static User currentUser;
    private static Scanner scanner;

    public static void main(String[] args) {
        users = new HashMap<>();
        scanner = new Scanner(System.in);

        while (true) {
            showMenu("Welcome to the Online Exam System",
                    "1. Register",
                    "2. Login",
                    "3. Quit"
            );

            int choice = getChoice();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting the Online Exam System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu(String title, String... options) {
        System.out.println("\n" + title);
        for (String option : options) {
            System.out.println(option);
        }
    }

    private static int getChoice() {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void register() {
        System.out.println("\nRegistration Form");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Registration failed.");
        } else {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            users.put(username, new User(username, password));
            System.out.println("Registration successful! You can now login.");
        }
    }

    private static void login() {
        System.out.println("\nLogin Form");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            showLoggedInMenu();
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }

    private static void showLoggedInMenu() {
        while (true) {
            showMenu("Main Menu",
                    "1. Update Profile",
                    "2. Update Password",
                    "3. Start Exam",
                    "4. Logout"
            );

            int choice = getChoice();

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    updatePassword();
                    break;
                case 3:
                    startExam();
                    break;
                case 4:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void updateProfile() {
        System.out.println("\nUpdate Profile");
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        currentUser.setName(newName);
        System.out.println("Profile updated successfully.");
    }

    private static void updatePassword() {
        System.out.println("\nUpdate Password");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (!currentUser.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect current password. Password update failed.");
        } else {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            currentUser.setPassword(newPassword);
            System.out.println("Password updated successfully.");
        }
    }

    private static void startExam() {
        System.out.println("\nStarting Exam...");

        // Simulating MCQs
        String[] questions = {
                "1. What is the capital of France?",
                "2. What is the symbol of water?",
                "3. What is the tallest mountain in the world?"
        };

        String[] options = {
                "A. London, B. Paris, C. Berlin, D. Rome",
                "A. O, B. H2O, C. CO2, D. C6H12O6",
                "A. Mount Kilimanjaro, B. Mount Fuji, C. Mount Everest, D. Mount McKinley"
        };

        String[] answers = {"B", "B", "C"};

        int totalQuestions = questions.length;
        int score = 0;

        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            System.out.println("Options: " + options[i]);
            System.out.print("Enter your answer (A/B/C/D): ");
            String userAnswer = scanner.nextLine().toUpperCase();

            if (userAnswer.equals(answers[i])) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + answers[i]);
            }
        }

        System.out.println("\nExam Completed!");
        System.out.println("Your Score: " + score + "/" + totalQuestions);
    }
}

class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "User";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
