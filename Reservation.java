/*Design a Reservation class that has the following members:
Fields:
• room (Room)
• guest (Guest)
• bookingStatus (BookingStatus)
• checkInDate (LocalDate)
• checkOutDate (LocalDate)
Make sure to validate that the check-out date is after the check-in date. Create an InvalidDateException
to handle this exception.
Methods:
• Overloaded constructors and appropriate setters and getters
• A toString()method */

import java.time.LocalDate;

public class Reservation {

    private Room room;
    private Guest guest;
    private BookingStatus bookingStatus;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    /**
     * No Arg ConstructorF
     */
    public Reservation() {
        this.room = new Room();
        this.guest = new Guest();
        this.bookingStatus = BookingStatus.PENDING;
        this.checkInDate = LocalDate.now();
        this.checkOutDate = checkInDate.plusDays(1);
    }

    /**
     * Constructor
     * 
     * @param room
     * @param guest
     * @param bookingStatus
     * @param checkInDate
     * @param checkOutDate
     */
    public Reservation(Room room, Guest guest, BookingStatus bookingStatus, LocalDate checkInDate,
            LocalDate checkOutDate) throws InvalidDateException {
        this.room = room;
        this.guest = guest;
        this.bookingStatus = bookingStatus;
        if (checkOutDate.isBefore(checkInDate)) {
            throw new InvalidDateException("Check out must be after check in.");
        }
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Setters
    public void setRoom(Room room) {
        this.room = room;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setCheckInDate(LocalDate checkInDate) throws InvalidDateException {
        if (checkInDate.isAfter(checkOutDate)) {
            throw new InvalidDateException("Check-in must be before check-out");
        }
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) throws InvalidDateException {
        if (checkOutDate.isBefore(checkInDate)) {
            throw new InvalidDateException("Check out must be after check in");
        }
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Room: " + room);
        str.append(" Guest: " + guest);
        str.append(" Booking Status: " + bookingStatus);
        str.append(" Check-In Date: " + checkInDate);
        str.append(" Check-Out Date: " + checkOutDate);
        return str.toString();
    }

    // equals method so i can use book a reservation while making sure theres no
    // duplicates
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        // cast
        Reservation r = (Reservation) other;
        return this.room.equals(r.room) && this.guest.equals(r.guest) && this.bookingStatus == r.bookingStatus
                && this.checkInDate.equals(r.checkInDate) && this.checkOutDate.equals(r.checkOutDate);
    }
}
