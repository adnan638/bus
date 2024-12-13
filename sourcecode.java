import java.io.*;
import java.util.Scanner;

// Encapsulation: Defining classes with private fields and public getters/setters.
class Seat {
    private int seatNumber;
    private String customerName;
    private boolean isOccupied;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.customerName = "Empty";
        this.isOccupied = false;
    }

    // Getter and Setter methods
    public int getSeatNumber() {
        return seatNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}

// Abstract Class for Bus functionality
abstract class AbstractBus {
    protected int busNumber;
    protected String schedule;
    protected String route;
    protected Seat[] seats;

    public AbstractBus(int busNumber, int maxSeats) {
        this.busNumber = busNumber;
        this.seats = new Seat[maxSeats];
        for (int i = 0; i < maxSeats; i++) {
            this.seats[i] = new Seat(i + 1);
        }
    }

    public abstract void displaySeats();
    public abstract void bookSeat(int seatNumber, String customerName);
}

// Concrete class inheriting abstract methods
class Bus extends AbstractBus {
    public Bus(int busNumber) {
        super(busNumber, 10); // Initialize with 10 seats
        this.schedule = "";
        this.route = "";
    }

    @Override
    public void displaySeats() {
        System.out.println("Seats in Bus " + busNumber + ":");
        for (Seat seat : seats) {
            String status = seat.isOccupied() ? "Occupied" : "Available";
            System.out.println("Seat " + seat.getSeatNumber() + ": " + status);
        }
    }

    @Override
    public void bookSeat(int seatNumber, String customerName) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number.");
            return;
        }
        Seat seat = seats[seatNumber - 1];
        if (seat.isOccupied()) {
            System.out.println("Seat is already occupied.");
        } else {
            seat.setOccupied(true);
            seat.setCustomerName(customerName);
            System.out.println("Seat " + seatNumber + " booked for " + customerName + ".");
            saveBookingToFile(seatNumber, customerName);
        }
    }

    // Save the booking details to a file
    private void saveBookingToFile(int seatNumber, String customerName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt", true))) {
            writer.write("Bus Number: " + busNumber + ", Seat Number: " + seatNumber + ", Customer Name: " + customerName);
            writer.newLine();
            System.out.println("Booking details saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }
    }

    // Encapsulation: Getters and Setters for schedule and route
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}

// Payment system interfaces and implementations
interface PaymentSystem {
    void processPayment(int amount);
}

class BkashPayment implements PaymentSystem {
    @Override
    public void processPayment(int amount) {
        System.out.println("Processing payment via Bkash...");
        System.out.println("Amount " + amount + " paid successfully.");
    }
}

class NagadPayment implements PaymentSystem {
    @Override
    public void processPayment(int amount) {
        System.out.println("Processing payment via Nagad...");
        System.out.println("Amount " + amount + " paid successfully.");
    }
}

// Main Application
public class BusTicketReservationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_BUSES = 5;
    private static final Bus[] buses = new Bus[MAX_BUSES];
    private static final String[] schedules = {"6AM", "8AM", "10AM", "1PM", "6PM"};
    private static final String[] routes = {"Dhaka to Kushtia", "Kushtia to Dhaka"};

    public static void main(String[] args) {
        // Initialize buses
        for (int i = 0; i < MAX_BUSES; i++) {
            buses[i] = new Bus(i + 1);
        }

        System.out.println("Welcome to the Bus Ticket Reservation System!");

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> displaySchedules();
                case 2 -> searchBusByRoute();
                case 3 -> addBus();
                case 4 -> selectSeat();
                case 5 -> makePayment();
                case 6 -> System.out.println("Thank you for using the system!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }

    // Displays menu options
    private static void displayMenu() {
        System.out.println("""
                \nMain Menu:
                1. Display Bus Schedules
                2. Search Bus by Route
                3. Add Bus
                4. Select a Seat
                5. Make Payment
                6. Exit
                Enter your choice:
                """);
    }

    // Display available bus schedules
    private static void displaySchedules() {
        System.out.println("Available Bus Schedules:");
        for (int i = 0; i < schedules.length; i++) {
            System.out.println((i + 1) + ". " + schedules[i]);
        }
    }

    private static void searchBusByRoute() {
        System.out.print("Enter route (Dhaka to Kushtia/Kushtia to Dhaka): ");
        String route = scanner.nextLine();

        System.out.println("Available buses for route: " + route);
        for (Bus bus : buses) {
            if (route.equalsIgnoreCase(bus.getRoute())) {
                System.out.println("Bus " + bus.busNumber + ": " + bus.getSchedule());
            }
        }
    }

    private static void addBus() {
        System.out.print("Enter bus number to add (1-5): ");
        int busNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (busNumber < 1 || busNumber > MAX_BUSES) {
            System.out.println("Invalid bus number.");
            return;
        }

        Bus bus = buses[busNumber - 1];
        System.out.print("Enter schedule: ");
        bus.setSchedule(scanner.nextLine());

        System.out.print("Enter route (Dhaka to Kushtia/Kushtia to Dhaka): ");
        bus.setRoute(scanner.nextLine());

        System.out.println("Bus added successfully!");
    }

    private static void selectSeat() {
        System.out.print("Enter bus number (1-5): ");
        int busNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (busNumber < 1 || busNumber > MAX_BUSES) {
            System.out.println("Invalid bus number.");
            return;
        }

        Bus bus = buses[busNumber - 1];
        bus.displaySeats();

        System.out.print("Enter seat number to book: ");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        bus.bookSeat(seatNumber, customerName);
    }

    private static void makePayment() {
        System.out.print("Enter total amount: ");
        int amount = scanner.nextInt();

        System.out.println("Select payment method: 1. Bkash 2. Nagad");
        int method = scanner.nextInt();

        PaymentSystem paymentSystem;
        if (method == 1) {
            paymentSystem = new BkashPayment();
        } else {
            paymentSystem = new NagadPayment();
        }

        paymentSystem.processPayment(amount);
    }
}

