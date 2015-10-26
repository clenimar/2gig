package models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.sound.midi.Instrument;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private List<Instrument> instruments;
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Ad> adList;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.adList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }


}
