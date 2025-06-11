public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
        super("Invalid date");
    }

    public InvalidDateException(String msg) {
        super(msg);
    }
}