
/*-firstName: String
-lastName: String
-gender: Gender
-address: Address
-birthdate: LocalDate
+Person(String f, String l, Gender g, Address add, String birthdate)
+Person(String f, String l, Gender g, String s, String c, String state, String zip, String birthdate)
+Person(String f, String l, Gender g, Address add, LocalDate birthdate)
+Person(Person p)
+getFirstName():String
+getLastName():String
+getGender():Gender
+getBirthdate():LocalDate
+getAddress(): Address
+setLastName(String last):void
+setAddress(Address addr):void
+toString(): String
+equals(Object o):boolean */
import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Address address;
    private LocalDate birthDate;
    private final static int ADULT = 21;

    // No Arg Constructor
    public Person() {
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.gender = Gender.M;
        this.address = new Address();
        this.birthDate = LocalDate.now();
    }

    public static int getAdultAge() {
        return ADULT;
    }

    /**
     * Constructor with data type Address and String birthDate
     * Person(String f, String l, Gender g, Address add, String birthdate)
     * 
     * @param firstName
     * @param lastName
     * @param gender
     * @param address
     * @param birthDate
     */
    public Person(String firstName, String lastName, Gender gender, Address address, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.birthDate = LocalDate.parse(birthDate);
    }

    /**
     * constructor with all parts of Address and birthdate are accepted as type
     * String
     * 
     * @param firstName
     * @param lastName
     * @param gender
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param birthDate
     */
    public Person(String firstName, String lastName, Gender gender, String street, String city, String state,
            String zip, String birthDate) {
        this(firstName, lastName, gender, new Address(street, city, state, zip), LocalDate.parse(birthDate));
    }

    /**
     * Constructor
     * Address and LocalDate types are used for parameters
     * 
     * @param firstName
     * @param lastName
     * @param gender
     * @param address
     * @param birthDate
     */
    public Person(String firstName, String lastName, Gender gender, Address address, LocalDate birthDate) {
        if (lastName == null || firstName == null)
            throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = new Address(address); // deep copy
        this.birthDate = birthDate;
    }

    /**
     * Copy constructor 
     * 
     * @param p
     */
    public Person(Person other) {
        this(other.firstName, other.lastName, other.gender, other.address, other.birthDate);
    }

    // Setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    /*
     * toString
     */

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("First Name: " + firstName);
        str.append(" Last Name: " + lastName);
        str.append(" Gender: " + gender);
        str.append(" Address: " + address.toString());
        str.append(" Birthday: " + birthDate.toString());
        return str.toString();
    }

    /*
     * equals method
     * The equals() method in the Person class should be based on last name, first
     * name, and birthdate
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
        Person p = (Person) other;
        if (this.firstName.equals(p.firstName)
                && this.lastName.equals(p.lastName)
                && this.birthDate.equals(p.birthDate)) {
            return true;
        }
        return false;
    }

}
