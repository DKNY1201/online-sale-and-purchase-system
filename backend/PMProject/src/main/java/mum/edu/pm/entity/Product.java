package mum.edu.pm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CascadeType;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "item")
public class Product implements Serializable {
	private static final long serialVersionUID = 5186013952828648626L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category")
	private String productCategory;

	@Column(name = "description")
	private String productDescription;

	@Column(name = "manufacturer")
	private String productManufacturer;

	@NotEmpty(message = "Product Name is mandatory")
	@Column(name = "name")
	private String productName;

	@NotNull(message = "Please provide some price")
	@Min(value = 100, message = "Minimum value should be greater than 100")
	@Column(name = "price")
	private double productPrice;

	@Column(name = "unit")
	private String unitStock;

	@Transient
	private MultipartFile productImage;

	@ManyToOne
	@JoinColumn(name = "vendorId")
	private User vendor;
	// Getters and Setter

	public Product() {
	}

	public String getProductCategory() {
		return productCategory;
	}

	public Long getId() {
		return id;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductManufacturer() {
		return productManufacturer;
	}

	public String getProductName() {
		return productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public String getUnitStock() {
		return unitStock;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public void setUnitStock(String unitStock) {
		this.unitStock = unitStock;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	// Constructors
	public Product(Long productId, String productCategory, String productDescription, String productManufacturer, String productName, double productPrice,
			String unitStock) {
		super();
		this.id = productId;
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productManufacturer = productManufacturer;
		this.productName = productName;
		this.productPrice = productPrice;
		this.unitStock = unitStock;
	}
}
