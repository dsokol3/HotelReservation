/*
 * Design a Room class that has the following members:
 * Fields:
 * • roomNumber (int)
 * • roomType (String) (e.g., Single, Double, Suite)
 * • isAvailable (boolean)
 * • nightlyRate (double)
 * Create an InvalidRateException to handle invalid rates.
 * Methods:
 * • Overloaded constructors and appropriate setters and getters
 * • A toString() method
 * • An equals() method based on roomNumber
 */
public class Room {

    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double nightlyRate;

    private static int nextRoomNumber = 1;

    /**
     * no arg constructor
     */
    public Room() {
        this.roomNumber = 0;
        this.roomType = "";
        this.isAvailable = false;
        this.nightlyRate = 0.0;
    }

    /**
     * parameter constructor
     * room number is automatically assigned to the nextRoomNumber static variable
     * 
     * @param roomNumber
     * @param roomType
     * @param isAvailable
     * @param nightlyRate
     */
    public Room(String roomType, double nightlyRate) throws InvalidRateException {
        this.roomNumber = nextRoomNumber;
        nextRoomNumber++;
        this.roomType = roomType;
        this.isAvailable = true;
        // input validation
        if (nightlyRate < 0) {
            throw new InvalidRateException("Nightly rate cannot be negative, you entered: " + nightlyRate);
        }
        this.nightlyRate = nightlyRate;
    }

    // setters
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setNightlyRate(double nightlyRate) throws InvalidRateException {
        if (nightlyRate < 0) {
            throw new InvalidRateException("Nightly rate cannot be negative");
        }
        this.nightlyRate = nightlyRate;
    }

    // getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public double getNightlyRate() {
        return nightlyRate;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Room Number: " + roomNumber);
        str.append(" Room Type: " + roomType);
        str.append(" Available: " + isAvailable);
        str.append(" Nightly Rate: $" + nightlyRate);
        return str.toString();
    }

    /*
     * equals method
     */
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
        Room r = (Room) other;
        return this.roomNumber == r.roomNumber;

    }
}