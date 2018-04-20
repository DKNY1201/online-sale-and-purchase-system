package mum.edu.pm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=6, max=50)
	@Column(unique=true)
	private String userName;
	
	@Size(min=6, max=250)
	private String passWord;
	
	@Enumerated(EnumType.STRING)
	private Role role;

//	@OneToMany // -> k generate.
	//@OneToMany --> generate new table: user_addresses
	//private List<Address> addresses;
	
//	public List<Address> getAddresses() {
//		return addresses;
//	}
	
	public User() {
//		addresses = new ArrayList<>();
	}

//	public void setAddresses(List<Address> addresses) {
//		this.addresses = addresses;
//	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Size(min=2, max=40)
	private String firstName;
	
	@Size(min=2, max=40)
	private String lastName;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	public User(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
