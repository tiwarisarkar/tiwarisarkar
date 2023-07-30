import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSystem {
    private static Map<String, String> users = new HashMap<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static int reservationIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOnline Reservation System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Make a Reservation");
            System.out.println("4. Cancel a Reservation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    if (isLoggedIn) {
                        makeReservation(scanner);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    if (isLoggedIn) {
                        cancelReservation(scanner);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the reservation system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("\nRegistration Form");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Registration failed.");
        } else {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            users.put(username, password);
            System.out.println("Registration successful! You can now login.");
        }
    }

    private static boolean isLoggedIn = false;
    private static String loggedInUser;

    private static void login(Scanner scanner) {
        System.out.println("\nLogin Form");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            isLoggedIn = true;
            loggedInUser = username;
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\nReservation Form");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the date of reservation: ");
        String date = scanner.nextLine();

        int reservationId = reservationIdCounter++;
        Reservation reservation = new Reservation(reservationId, loggedInUser, name, date);
        reservations.put(reservationId, reservation);

        System.out.println("Reservation details:");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("\nCancellation Form");

        System.out.print("Enter your reservation ID: ");
        int reservationId = Integer.parseInt(scanner.nextLine());

        if (reservations.containsKey(reservationId)) {
            Reservation reservation = reservations.get(reservationId);
            if (reservation.getReservedBy().equals(loggedInUser)) {
                reservations.remove(reservationId);
                System.out.println("Reservation with ID " + reservationId + " has been canceled.");
            } else {
                System.out.println("You can only cancel your own reservation.");
            }
        } else {
            System.out.println("Invalid reservation ID. Cancellation failed.");
        }
    }

    private static class Reservation {
        private int reservationId;
        private String reservedBy;
        private String name;
        private String date;

        public Reservation(int reservationId, String reservedBy, String name, String date) {
            this.reservationId = reservationId;
            this.reservedBy = reservedBy;
            this.name = name;
            this.date = date;
        }

        public int getReservationId() {
            return reservationId;
        }

        public String getReservedBy() {
            return reservedBy;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }
    }
}
