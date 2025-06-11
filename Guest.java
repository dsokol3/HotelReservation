/*
 * Guest
 * -reservationNumber: String
 * -phoneNumber: String
 * -email: String
 * +Guest(String f, String l, Gender g, Address addr, String
 * birthdate, String reservationNumber, String phoneNumber,
 * String email)
 * +Guest(Guest g)
 * +setReservationNumber(String reservationNumber):void
 * +setPhoneNumber(String phoneNumber):void
 * +setEmail(String email):void
 * +getReservationNumber():String
 * +getPhoneNumber():String
 * +getEmail():String
 * +toString():String
 * +equals(Object o):boolean
 */

public class Guest extends Person {

    private String reservationNumber;
    private String phoneNumber;
    private String email;

    /**
     * No Arg Constructor
     */
    public Guest() {
        super();
        this.reservationNumber = "";
        this.phoneNumber = "";
        this.email = "";
    }

    /**
     * Parameter Constructor
     * 
     * @param firstName
     * @param lastName
     * @param gender
     * @param address
     * @param birthday
     * @param reservationNumber
     * @param phoneNumber
     * @param email
     */
    public Guest(String firstName, String lastName, Gender gender, Address address, String birthday,
            String reservationNumber, String phoneNumber, String email) {
        super(firstName, lastName, gender, address, birthday);
        this.reservationNumber = reservationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Copy Constructor
     * 
     * @param g
     */
    public Guest(Guest g) {
        super(g);
        this.reservationNumber = g.reservationNumber;
        this.phoneNumber = g.phoneNumber;
        this.email = g.email;
    }

    // setters
    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // getters
    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    /**
     * toString
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append("Reservation Number " + reservationNumber);
        str.append(" Phone Number " + phoneNumber);
        str.append(" Email: " + email);
        return str.toString();
    }

    /**
     * equals method
     * The equals() method in the Guest class should be based on the
     * reservationNumber.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        // cast
        Guest g = (Guest) other;
        if (this.reservationNumber.equals(g.reservationNumber)) {
            return true;
        }
        return false;
    }

}