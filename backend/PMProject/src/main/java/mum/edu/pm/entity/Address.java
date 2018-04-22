package mum.edu.pm.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -949575859121821378L;
	@Id
    @GeneratedValue
    private Long id;
    //private Long usedId;
    private String street;
    private String city;
    private String state;
    private String zipcode; // Int

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    private String phoneNum;

    public Address() {

    }

    public Address(String street, String city, String state, String zipcode, String phoneNum) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.user = user;
        this.phoneNum = phoneNum;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
