package models;

import javax.persistence.*;
import java.util.List;

public class Ad {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String author;
    @Column
    private Address address;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Instrument> instruments;
    @Column
    private List<String> desiredStyles;
    @Column
    private List<String> undesiredStyles;
    @Column
    private int casualOrBand;
    @Column
    private String passwd;
    @ManyToMany(cascade = CascadeType.ALL)
    private Contact contact;

    public Ad(String author, String title, String description, Address address, Contact contact,
              List<Instrument> visibleInstruments, List<String> desiredStyles, List<String> undesiredStyles,
              int casualOrBand, String passwd) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.address = address;
        this.instruments = visibleInstruments;
        this.desiredStyles = desiredStyles;
        this.undesiredStyles = undesiredStyles;
        this.casualOrBand = casualOrBand;
        this.passwd = passwd;
        this.contact = contact;
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

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
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

    public int getCasualOrBand() {
        return casualOrBand;
    }

    public void setCasualOrBand(int casualOrBand) {
        this.casualOrBand = casualOrBand;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
