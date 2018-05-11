package edu.mum.onlineshopping.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address = new Address();
	private String phone;
	private boolean enable;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
//	private List<Product> products = new ArrayList<>();
	
	@Transient
	private String password;
	
	@Transient
	private Integer role;

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void addProduct(Product product) {
//		if (this.getRole() == 3 && !this.getProducts().contains(product)) {
//			this.products.add(product);
//			product.setPerson(this);
//		}
//	}

	public long getId() {
		return id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}
