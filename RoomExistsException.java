public class RoomExistsException extends RuntimeException {
    public RoomExistsException() {
        super("Room already in the system");
    }

    public RoomExistsException(String msg) {
        super(msg);
    }
}