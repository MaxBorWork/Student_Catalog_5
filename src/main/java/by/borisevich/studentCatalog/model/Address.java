package by.borisevich.studentCatalog.model;

public class Address {

    private int id;
    private String street;
    private int house;
    private int flat;

    public Address(int id, String street, int house, int flat) {
        this.id = id;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }
}
