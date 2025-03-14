public class Address {
    private String name;
    private String street;
    private int addressNum;
    private String city;
    private String state;
    private int zipCode;


    public Address(String name, String street, int addressNum, String city, String state, int zipCode) {
        this.name = name;
        this.street = street;
        this.addressNum = addressNum;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAddressNum() {
        return addressNum;
    }

    public void setAddressNum(int addressNum) {
        this.addressNum = addressNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address " +
                name + '\n' +
                addressNum + " " + street + "\n"
                + city + ", " + state + " " + zipCode;
    }
}
