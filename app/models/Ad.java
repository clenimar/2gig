package models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Ad {
    @Id
    @GeneratedValue
    private long id;
    // ad info:
    private String author;
    private String title;
    private String description;
    private String interest;
    private long when;
    // address info:
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    // contact info:
    private String email;
    private String facebook;
    private String phone;
    // misc:
    @ElementCollection
    private List<String> instruments;
    @ElementCollection
    private List<String> desiredStyles;
    @ElementCollection
    private List<String> undesiredStyles;
    // security:
    private String passwd;

    public Ad(){
    }

    public Ad(String author, String title, String description, String street, String number, String neighbourhood,
              String city, String state, String country, String email, String phone,
              List<String> instruments, List<String> desiredStyles, List<String> undesiredStyles,
              String interest, String passwd) {
        this();
        this.author = author;
        this.title = title;
        this.description = description;
        this.instruments = instruments;
        this.desiredStyles = desiredStyles;
        this.undesiredStyles = undesiredStyles;
        this.interest = interest;
        this.passwd = passwd;
        this.street = street;
        this.number = number;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.when = Calendar.getInstance().getTimeInMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<String> instruments) {
        this.instruments = instruments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDesiredStyles() {
        return desiredStyles;
    }

    public void setDesiredStyles(List<String> desiredStyles) {
        this.desiredStyles = desiredStyles;
    }

    public List<String> getUndesiredStyles() {
        return undesiredStyles;
    }

    public void setUndesiredStyles(List<String> undesiredStyles) {
        this.undesiredStyles = undesiredStyles;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }
}
