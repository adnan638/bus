Bus Ticket Reservation System
This is a simple Bus Ticket Reservation System implemented in Java. The system allows users to view bus schedules, book seats, make payments, and manage bus information. It also demonstrates object-oriented programming principles like Encapsulation, Abstraction, and Polymorphism.

Features
View Bus Schedules: Displays available bus schedules.
Search Bus by Route: Allows users to search buses based on routes.
Add Bus: Lets users add new bus information (schedule and route).
Select Seat: Allows users to book seats on a bus.
Make Payment: Processes payments using different payment methods (e.g., Bkash or Nagad).
File Persistence: Booking details are saved to a file (bookings.txt).
Key Concepts Implemented
Encapsulation: Using private fields and public getters/setters for encapsulating bus and seat details.
Abstraction: The system uses abstract classes for bus-related functionality.
Polymorphism: The payment system uses interfaces and classes to handle different payment methods (BkashPayment, NagadPayment).
Requirements
Java 8 or higher
Basic knowledge of Object-Oriented Programming concepts
How to Run
Clone the repository to your local machine:

bash
Copy code
git clone https://github.com/your-username/bus-ticket-reservation-system.git
Compile the program:

bash
Copy code
javac BusTicketReservationSystem.java
Run the program:

bash
Copy code
java BusTicketReservationSystem
Usage
Upon running the system, the user will see a menu with the following options:

Display Bus Schedules: Displays all available bus schedules.
Search Bus by Route: Allows searching for buses by route (Dhaka to Kushtia / Kushtia to Dhaka).
Add Bus: Users can add a new bus with schedule and route information.
Select a Seat: Users can book a seat on a bus by selecting a seat number.
Make Payment: Users can make payments using the Bkash or Nagad payment method.
