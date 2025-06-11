/*-street: String
-city: String
-state: String
-zipcode: String
+Address(String street, String city, String state, String zip)
+getStreet(): String
+getCity(): String
+getState(): String
+getZip(): String
+toString(): String
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    // No Arg Constructor
    public Address() {
        this.street = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
    }

    /**
     * Constructor
     * Address(String street, String city, String state, String zipCode)
     * 
     * @param street
     * @param city
     * @param state
     * @param zipCode
     */
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Copy constructor to help deep copy in Person class
     * 
     * @param other
     */
    public Address(Address other) {
        this.street = other.street;
        this.city = other.city;
        this.state = other.state;
        this.zipCode = other.zipCode;
    }

    // setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("City: " + city);
        str.append(" Street: " + street);
        str.append(" State: " + state);
        str.append(" Zip Code: " + zipCode);
        return str.toString();
    }

    /**
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
        Address a = (Address) other;
        if (this.street.equals(a.street)
                && this.city.equals(a.city)
                && this.state.equals(a.state)
                && this.zipCode.equals(a.zipCode)) {
            return true;
        }
        return false;
    }
}