package models;

import javax.persistence.*;

@Entity
@Table
public class Instrument {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Instrument() {

    }

    public Instrument(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}