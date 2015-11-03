package models;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private long id;

    private String email;
    private String facebookUrl;
    private String phone1;
    private String phone2;

    public Contact(){

    }

    public Contact(String email, String phone1) {
        this();
        this.email = email;
        this.phone1 = phone1;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
