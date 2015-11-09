package models;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
    // contact info:
    private String email;
    private String facebook;
    private String phone;
    // misc:
    private String instruments;
    private String desiredStyles;
    private String undesiredStyles;
    // security:
    private String passwd;
    // status:
    private boolean closed;
    private String feedback;
    // comments:
    @OneToMany
    private List<Comment> comments;

    public Ad(){
    }

    public Ad(String author, String title, String description, String street, String number, String neighbourhood,
              String city, String state, HashMap<String, String> contact,
              String instruments, String desiredStyles, String undesiredStyles,
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
        this.when = Calendar.getInstance().getTimeInMillis();
        this.closed = false;
        this.feedback = "";
        this.comments = new ArrayList<>();
        setContact(contact);
    }

    private void setContact(HashMap<String, String> contact) {
        if (contact.containsKey("email"))
            this.email = contact.get("email");
        if (contact.containsKey("facebook"))
            this.facebook = contact.get("facebook");
        if (contact.containsKey("phone1"))
            this.phone = contact.get("phone1");
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

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesiredStyles() {
        return desiredStyles;
    }

    public void setDesiredStyles(String desiredStyles) {
        this.desiredStyles = desiredStyles;
    }

    public String getUndesiredStyles() {
        return undesiredStyles;
    }

    public void setUndesiredStyles(String undesiredStyles) {
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

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        this.closed = true;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean checkPassword(String p) {
        return DigestUtils.sha1Hex(p).equals(this.getPasswd());
    }

    public boolean addComent(Comment comment) {
        return this.comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
