package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Address {

    @Id
    @GeneratedValue
    private long id;

    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private String code;

    public Address(String street, String number, String neighbourhood, String city, String state, String country,
                   String code) {
        this.street = street;
        this.number = number;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
