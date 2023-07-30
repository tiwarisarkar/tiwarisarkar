import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}

class ATM {
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new ArrayList<>();
        // You can add sample users here for testing purposes
        users.add(new User("user1", "1234", 1000.0));
        users.add(new User("user2", "5678", 500.0));
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nWelcome to the ATM");
            System.out.print("Enter your user id: ");
            String userId = scanner.nextLine();

            System.out.print("Enter your user pin: ");
            String userPin = scanner.nextLine();

            User user = findUser(userId, userPin);
            if (user != null) {
                currentUser = user;
                showLoggedInMenu();
            } else {
                System.out.println("Invalid user id or pin. Please try again.");
            }
        }
    }

    private User findUser(String userId, String userPin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPin().equals(userPin)) {
                return user;
            }
        }
        return null;
    }

    private void showMenu(String title, String... options) {
        System.out.println("\n" + title);
        for (String option : options) {
            System.out.println(option);
        }
    }

    private int getChoice() {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void showLoggedInMenu() {
        while (true) {
            showMenu("Main Menu",
                    "1. Transactions History",
                    "2. Withdraw",
                    "3. Deposit",
                    "4. Transfer",
                    "5. Quit"
            );

            int choice = getChoice();

            switch (choice) {
                case 1:
                    showTransactionsHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionsHistory() {
        System.out.println("\nTransactions History for User: " + currentUser.getUserId());
        for (String transaction : currentUser.getTransactionsHistory()) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal failed.");
        } else if (currentUser.getBalance() < amount) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addTransaction("Withdraw: " + amount);
            System.out.println("Withdrawal successful. Current balance: " + currentUser.getBalance());
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit failed.");
        } else {
            currentUser.setBalance(currentUser.getBalance() + amount);
            currentUser.addTransaction("Deposit: " + amount);
            System.out.println("Deposit successful. Current balance: " + currentUser.getBalance());
        }
    }

    private void transfer() {
        System.out.print("Enter the recipient's user id: ");
        String recipientId = scanner.nextLine();

        User recipient = findUser(recipientId, "");

        if (recipient == null) {
            System.out.println("Recipient user not found. Transfer failed.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount <= 0) {
            System.out.println("Invalid amount. Transfer failed.");
        } else if (currentUser.getBalance() < amount) {
            System.out.println("Insufficient balance. Transfer failed.");
        } else {
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            currentUser.addTransaction("Transfer to " + recipient.getUserId() + ": " + amount);
            recipient.addTransaction("Transfer from " + currentUser.getUserId() + ": " + amount);
            System.out.println("Transfer successful. Current balance: " + currentUser.getBalance());
        }
    }
}

class User {
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionsHistory;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionsHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getTransactionsHistory() {
        return transactionsHistory;
    }

    public void addTransaction(String transaction) {
        transactionsHistory.add(transaction);
    }
}
