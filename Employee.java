/*-employeeID: String
-role: String
-department: String
+Employee(String f, String l, Gender g, Address
addr, String birthdate, String employeeID,
String role, String department)
+Employee(Employee e)
+setEmployeeID(String employeeID):void
+setRole(String role):void
+setDepartment(String department):void
+getEmployeeID():String
+getDepartment():String
+getRole():String
+toString():String
+equals(Object o):boolean */

public class Employee extends Person {
    private String employeeID;
    private String role;
    private String department;

    // No Arg Constructor
    public Employee() {
        super();
        this.employeeID = "";
        this.role = "";
        this.department = "";
    }

    /**
     * Constructor
     * Employee(String f, String l, Gender g, Address addr, String birthdate, String
     * employeeID, String role, String department)
     * 
     * @param employeeID
     * @param role
     * @param department
     */
    public Employee(String firstName, String lastName, Gender gender, Address address, String birthday,
            String employeeID, String role, String department) {
        super(firstName, lastName, gender, address, birthday);
        this.employeeID = employeeID;
        this.role = role;
        this.department = department;
    }

    /**
     * Copy constructor
     * Employee(Employee e)
     * 
     * @param other
     */
    public Employee(Employee other) {
        super(other);
        this.employeeID = other.employeeID;
        this.role = other.role;
        this.department = other.department;
    }

    // Setters
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Getters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Employee ID: " + employeeID);
        str.append(" Role: " + role);
        str.append(" Department " + department);
        return str.toString();
    }

    /**
     * equals method
     * The equals() method in the Employee class should be based on employeeID.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if ((other == this)) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        // cast
        Employee e = (Employee) other;
        return this.employeeID.equals(e.employeeID);
    }
}