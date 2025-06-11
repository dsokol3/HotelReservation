public class EmployeeExistsException extends Exception {
    public EmployeeExistsException() {
        super("Employee is already in the system");
    }

    public EmployeeExistsException(String msg) {
        super(msg);
    }
}