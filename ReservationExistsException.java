public class ReservationExistsException extends RuntimeException {
    public ReservationExistsException() {
        super("Room already in the system");
    }

    public ReservationExistsException(String msg) {
        super(msg);
    }
}