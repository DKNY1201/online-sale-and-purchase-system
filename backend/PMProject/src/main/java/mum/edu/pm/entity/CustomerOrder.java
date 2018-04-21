package mum.edu.pm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customerorder")
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = -6571020025726257848L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private User customer;

	private String shippingAddress;

	private String billingAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId")
	private Payment payment;

	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems;

	public CustomerOrder() {
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Long getCustomerOrderId() {
		return id;
	}

	public void setCustomerOrderId(Long customerOrderId) {
		this.id = customerOrderId;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItem(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

}