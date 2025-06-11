public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
        super("Reservation not found");
    }

    public ReservationNotFoundException(String msg) {
        super(msg);
    }
}