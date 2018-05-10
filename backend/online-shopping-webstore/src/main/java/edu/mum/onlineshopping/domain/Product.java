package edu.mum.onlineshopping.domain;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int id;
	private String productName;
	@Column(columnDefinition="TEXT")
	private String description;
	private double price;
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	private String productImage;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	public Product() {
		super(); // default constructor
	}

	public Product(String productName, String description, double price, ProductType productType, String productImage) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.productType = productType;
		this.productImage = productImage;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
