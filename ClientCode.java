
/*
 * In your client code, make sure to instantiate a Hotel object. 
 * Add rooms, guests, and employees to the hotel. Display details about the rooms, guests, and employees. 
 * Provide a menu-driven interface for users to interact with the hotel reservation system. 
 * Implement functionalities for booking reservations, cancelling reservations, viewing room details, and viewing guest details. 
 * Include try-catch blocks to handle exceptions thrown by your classes.
 */
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientCode {
    /**
     * instantiate a Hotel object
     * Then, add rooms and employees
     * 
     * @param kb
     * @return hotel
     */
    public static Hotel setupHotel(Scanner kb) {
        Hotel hotel = new Hotel();
        addRooms(kb, hotel);
        addEmployees(kb, hotel);
        return hotel;
    }

    /**
     * add rooms to set up the hotel
     * 
     * @param kb
     * @param hotel
     */
    public static void addRooms(Scanner kb, Hotel hotel) {
        try {
            double nightlyRateSingle = 150;
            double nightlyRateDouble = 250;
            double nightlyRateSuite = 400;
            int amountOfRooms = 5;
            for (int i = 0; i < amountOfRooms; i++) {
                hotel.addRoom(new Room("Single", nightlyRateSingle));
                hotel.addRoom(new Room("Double", nightlyRateDouble));
                hotel.addRoom(new Room("Suite", nightlyRateSuite));
            }

        } catch (RoomExistsException e) {
            System.out.println("Room already exists: " + e.getMessage());
        } catch (InvalidRateException e) {
            System.out.println("Invalid rate: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    /**
     * add employees to set up the hotel
     * 
     * @param kb
     * @param hotel
     */
    public static void addEmployees(Scanner kb, Hotel hotel) {
        int numEmployees = 3;
        for (int i = 0; i < numEmployees; i++) {
            try {
                System.out.print("Enter employee first name: ");
                String firstName = kb.nextLine();
                System.out.print("Enter employee last name: ");
                String lastName = kb.nextLine();
                System.out.print("Enter gender (M/F): ");
                Gender gender = Gender.valueOf(kb.nextLine().toUpperCase());

                Address address = createAddress(kb);

                System.out.print("Enter birthdate (YYYY-MM-DD): ");
                String birthdate = kb.nextLine();
                System.out.print("Enter employee ID: ");
                String employeeID = kb.nextLine();
                System.out.print("Enter role: ");
                String role = kb.nextLine();
                System.out.print("Enter department: ");
                String department = kb.nextLine();

                hotel.addEmployee(new Employee(firstName, lastName, gender, address, birthdate,
                        employeeID, role, department));

            } catch (EmployeeExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * read in an Address from Scanner kb
     * 
     * @param kb
     * @return new Address
     */
    public static Address createAddress(Scanner kb) {
        System.out.print("Enter street address: ");
        String street = kb.nextLine();
        System.out.print("Enter city: ");
        String city = kb.nextLine();
        System.out.print("Enter state: ");
        String state = kb.nextLine();
        System.out.print("Enter zip code: ");
        String zip = kb.nextLine();
        return new Address(street, city, state, zip);
    }

    /**
     * read in info to create guest
     * 
     * @param kb
     * @return
     */
    public static Guest createGuest(Scanner kb) {
        System.out.print("Enter guest first name: ");
        String firstName = kb.nextLine();
        System.out.print("Enter guest last name: ");
        String lastName = kb.nextLine();
        System.out.print("Enter gender (M/F): ");
        Gender gender = Gender.valueOf(kb.nextLine().toUpperCase());

        Address address = createAddress(kb);

        System.out.print("Enter birthdate (YYYY-MM-DD): ");
        String birthdate = kb.nextLine();
        System.out.print("Enter reservation number: ");
        String reservationNumber = kb.nextLine();
        System.out.print("Enter phone number: ");
        String phone = kb.nextLine();
        System.out.print("Enter email: ");
        String email = kb.nextLine();

        Guest guest = new Guest(firstName, lastName, gender, address, birthdate,
                reservationNumber, phone, email);
        return guest;
    }

    /**
     * Book a reservation
     * To Book a reservation:
     * 1. read in information about a guest
     * 2. let user choose a room
     * 3. let user enter dates
     * 4. create reservation and change BookingStatus to CONFIRMED
     * 
     * @param kb
     * @param hotel
     */
    public static void bookReservation(Scanner kb, Hotel hotel) {
        try {
            boolean empty = true;
            for (Room r : hotel.getRooms()) {
                if (r.getIsAvailable()) {
                    empty = false;
                }
            }
            if (empty) {
                System.out.println("We're Sorry! There are no rooms available");
                return;
            }
            Guest guest = createGuest(kb);
            Room selectedRoom = chooseRoom(kb, hotel);
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            LocalDate checkIn = LocalDate.parse(kb.nextLine());
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            LocalDate checkOut = LocalDate.parse(kb.nextLine());

            Reservation reservation = new Reservation(selectedRoom, guest,
                    BookingStatus.CONFIRMED, checkIn,
                    checkOut);

            hotel.bookRoom(reservation);
            System.out.println("Reservation Booked");
        } catch (ReservationExistsException e) {
            System.out.println(e.getMessage());
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This method lets the user choose a room in the hotel
     * 1. ask user what room type
     * 2. print available rooms for the room type chosen
     * 3. pick a room by room number of the available list and validate
     * 4. return room
     * 
     * @param sc
     * @param hotel
     * @return selected room
     */
    public static Room chooseRoom(Scanner sc, Hotel hotel) {
        System.out.println("Available rooms:");
        displayRoomInfo(hotel.getRooms(), false);
        System.out.println("Pick a room by room number");

        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = hotel.findRoomByNum(roomNumber);
        // validate the room is available and the correct room type
        while (selectedRoom == null || !selectedRoom.getIsAvailable()) {
            System.out.println("There is an ERROR with that room number");
            System.out.println("Pick a room by a room number on the above list:");
            roomNumber = sc.nextInt();
            sc.nextLine();
            selectedRoom = hotel.findRoomByNum(roomNumber);
        }
        return selectedRoom;
    }

    /**
     * Cancel an existing reservation
     * 
     * @param kb
     * @param hotel
     */
    public static void cancelReservation(Scanner kb, Hotel hotel) {
        try {
            System.out.print("Enter reservation number to cancel: ");
            String reservationNumber = kb.nextLine();
            hotel.cancelReservation(reservationNumber);
            System.out.println("Reservation cancelled");
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print all or only available rooms in a hotel
     * Accepts list of rooms from a hotel and a boolean parameter
     * if printAll is true it prints all the hotel rooms (labeling the unavailable
     * rooms as "Booked")
     * otherwise it only prints the available rooms
     * 
     * @param rooms
     * @param printAll
     */
    public static void displayRoomInfo(ArrayList<Room> rooms, boolean printAll) {
        for (Room r : rooms) {
            if (r.getIsAvailable()) {
                System.out.println(r);
            } else if (printAll) {
                System.out.println("Room Number: " + r.getRoomNumber() +
                        " Room Type: " + r.getRoomType() + " Status: Booked");
            }
        }
    }

    /**
     * Print all guests in the hotel
     * accepts list of reservations from the hotel and gets each reservation's guest
     * and prints guest info
     * 
     * @param reservations
     */
    public static void displayGuestInfo(ArrayList<Reservation> reservations) {
        for (Reservation r : reservations) {
            System.out.println(r.getGuest());
        }

    }

    /**
     * print employees
     * accepts list of employees to print
     * 
     * @param employees
     */
    public static void displayEmployeeInfo(ArrayList<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    /**
     * Print Menu
     */
    public static void displayMenu() {
        System.out.println(" HOTEL RESERVATION SYSTEM ");
        System.out.println("1. Book Reservation");
        System.out.println("2. Cancel Reservation");
        System.out.println("3. View Room Details");
        System.out.println("4. View Guest Details");
        System.out.println("5. View Employee Details");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Instantiate a Hotel object using method setUpHotel() to add rooms and
     * employees to the hotel.
     * Provides a menu-driven interface for users to interact with the hotel
     * reservation system.
     * The menu choices are: booking a reservation, cancelling
     * a reservation, viewing all room details, viewing all guest details and
     * viewing all employee details.
     * Exceptions are caught in individual methods.
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Scanner kb = new Scanner(System.in);
        Hotel hotel = setupHotel(kb);

        int choice = 0;
        while (choice != 6) {
            displayMenu();
            choice = kb.nextInt();
            kb.nextLine();

            switch (choice) {
                case 1: // Book Reservation
                    bookReservation(kb, hotel);
                    break;

                case 2: // Cancel Reservation
                    cancelReservation(kb, hotel);
                    break;

                case 3: // View Room Details
                    displayRoomInfo(hotel.getRooms(), true);
                    break;

                case 4: // View Guest Details
                    displayGuestInfo(hotel.getReservations());
                    break;
                case 5: // view Employee details
                    displayEmployeeInfo(hotel.getEmployees());
                    break;
                case 6:
                    System.out.println("Thank you for using Hotel Reservation System by Devora Sokol!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
