/*
Design a Hotel class that has the following members:
Fields:
• An ArrayList of rooms (ArrayList<Room> rooms)
• An ArrayList of reservations (ArrayList<Reservation> reservations)
• An ArrayList of employees (ArrayList<Employee> employees)
Methods:
• Overloaded constructors (a no-arg constructor should just instantiate the ArrayLists) and appropriate
setters and getters. Deep copies of the ArrayLists should be implemented in the constructors, setters,
and getters.
• A toString()method
• A method to add a room to the hotel if it does not already exist. Otherwise, it should throw an
exception, which you will create, called RoomExistsException. Remember to handle this exception in
your client code.
• A method to book a room (add a reservation to the ArrayList of reservations) if the reservation does
not already exist. Otherwise, it should throw an exception, which you will create, called
ReservationExistsException. Remember to handle this exception in your client code.
• A method to cancel a reservation – the method should receive a reservation number and remove a
reservation from the ArrayList of reservations if a guest on the reservation list has that reservation
number. Otherwise, it should throw exception ReservationNotFoundException. You should create
this exception and handle it in your client code.
• A method to find information about a specific room – the method should receive the room number
and return the room in the ArrayList with that number. Otherwise, it should return null.
• A method to find information about a specific guest with a reservation – the method should receive
the reservation number and return the guest with that reservation number in the reservation ArrayList.
Otherwise, it should return null.
• A method to add an employee to the hotel (add an employee to the ArrayList of employees if that
employee is not already in the ArrayList). Otherwise, it should throw an exception, which you will
create, called EmployeeExistsException. Remember to handle this exception in your client code.
• A method to find information about a specific employee - the method should receive the employee’s
ID and return the employee with that ID in the employees ArrayList. Otherwise, it should return null */

import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private ArrayList<Employee> employees;

    /**
     * no arg constructor
     */
    public Hotel() {
        this.rooms = new ArrayList<Room>();
        this.reservations = new ArrayList<Reservation>();
        this.employees = new ArrayList<Employee>();
    }

    public Hotel(ArrayList<Room> rooms, ArrayList<Reservation> reservations, ArrayList<Employee> employees) {
        this.rooms = new ArrayList<>(rooms);
        this.reservations = new ArrayList<>(reservations);
        this.employees = new ArrayList<>(employees);
    }

    // setters
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = new ArrayList<Room>(rooms);
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = new ArrayList<Reservation>(reservations);
    }

    public void setEmployee(ArrayList<Employee> employees) {
        this.employees = new ArrayList<Employee>(employees);
    }

    // getters

    public ArrayList<Room> getRooms() {
        return new ArrayList<Room>(rooms);
    }

    public ArrayList<Reservation> getReservations() {
        return new ArrayList<Reservation>(reservations);
    }

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<Employee>(employees);
    }

    /**
     * toString method
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rooms: " + rooms);
        str.append("\nReservation: " + reservations);
        str.append("\nEmployee: " + employees);
        return str.toString();
    }

    /**
     * • A method to add a room to the hotel if it does not already exist.
     * Otherwise, it should throw an exception, which you will create, called
     * RoomExistsException. Remember to handle this exception in your client code.
     * 
     * @param newRoom the Room to add
     * @throws RoomExistsException if the room already exists in the list
     */
    public void addRoom(Room newRoom) throws RoomExistsException {
        if (rooms.contains(newRoom)) {
            throw new RoomExistsException("Room already exists");
        }
        rooms.add(newRoom);
    }

    /**
     * • A method to book a room (add a reservation to the ArrayList of
     * reservations) if the reservation does not already exist. Otherwise, it should
     * throw an exception, which you will
     * create, called ReservationExistsException. Remember to handle this exception
     * in your client code.
     */

    public void bookRoom(Reservation newReservation) throws ReservationExistsException {

        if (reservations.contains(newReservation)) {
            throw new ReservationExistsException("Room is already reserved");
        }
        // Mark room as unavailable after someone books
        newReservation.getRoom().setIsAvailable(false);
        reservations.add(newReservation);
    }

    /**
     * • A method to cancel a reservation – the method should receive a reservation
     * number and remove a reservation from the ArrayList of reservations if a guest
     * on the reservation list has that reservation number. Otherwise, it should
     * throw exception ReservationNotFoundException.
     * You should create this exception and handle it in your client code.
     * 
     * @param reservationNumber
     * @throws ReservationNotFoundException
     */
    public void cancelReservation(String reservationNumber) throws ReservationNotFoundException {
        boolean valid = false;
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getGuest().getReservationNumber().equals(reservationNumber)) {
                reservation.getRoom().setIsAvailable(true);
                reservations.remove(i);
                valid = true;
            }
        }
        if (valid == false) {
            throw new ReservationNotFoundException("Invalid reservation number");
        }

    }

    /**
     * • A method to find information about a specific room – the method should
     * receive the room number
     * and return the room in the ArrayList with that number. Otherwise, it should
     * return null.
     * 
     * @param roomNumber
     * @return
     */
    public Room findRoomByNum(int roomNumber) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNumber) {
                return rooms.get(i);

            }
        }
        return null;
    }

    /**
     * • A method to find information about a specific guest with a reservation –
     * the method should receive the reservation number and return the guest with
     * that reservation number in the reservation ArrayList. Otherwise, it should
     * return null.
     * 
     * @param num
     * @return
     */
    public Guest findGuestByReservationNum(String num) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getGuest().getReservationNumber().equals(num)) {
                return reservations.get(i).getGuest();
            }
        }

        return null;
    }

    /**
     * • A method to add an employee to the hotel (add an employee to the ArrayList
     * of employees if that employee is not already in the ArrayList). Otherwise, it
     * should throw an exception, which you will create, called
     * EmployeeExistsException. Remember to handle this exception in your client
     * code.
     * 
     * @param employee
     * @throws EmployeeExistsException
     */
    public void addEmployee(Employee employee) throws EmployeeExistsException {
        if (employees.contains(employee)) {
            throw new EmployeeExistsException("Employee is already in the system");
        }
        employees.add(new Employee(employee));

    }

    /**
     * • A method to find information about a specific employee - the method should
     * receive the employee’s ID and return the employee with that ID in the
     * employees ArrayList. Otherwise, it should return null
     * 
     * @param ID
     * @return
     */
    public Employee findEmployeeById(String ID) {
        for (int i = 0; i < employees.size(); i++) {
            if (ID.equalsIgnoreCase(employees.get(i).getEmployeeID())) {
                return employees.get(i);
            }
        }
        return null;
    }

}