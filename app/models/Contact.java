package models;

import javax.persistence.Column;

public class Contact {
    @Column
    private String email;
    @Column
    private String facebookUrl;
    @Column
    private String phone1;
    @Column
    private String phone2;

    public Contact(String email, String phone1) {
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
