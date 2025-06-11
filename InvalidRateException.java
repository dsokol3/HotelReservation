public class InvalidRateException extends RuntimeException {
    public InvalidRateException() {
        super("Invalid rate");
    }

    public InvalidRateException(String msg) {
        super(msg);
    }

}