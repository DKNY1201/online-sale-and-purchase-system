package edu.mum.onlineshopping.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Orderline> orderLines = new ArrayList<Orderline>();
	@OneToOne
	private Person person;

	public int getId() {
		return id;
	}

	public List<Orderline> getOrderLines() {
//		return Collections.unmodifiableList(orderLines);
		return orderLines;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		int quantity = 0;
		for (Orderline ol : this.orderLines) {
			quantity += ol.getQuantity();
		}
		return quantity;
	}

	public double getTotalAmount() {
		double totalAmount = 0;

		for (Orderline ol : this.orderLines) {
			totalAmount += ol.getSubtotal();
		}
		return totalAmount;
	}

	public void addOrderLine(Orderline orderLine) {
		orderLine.setOrder(this);
		boolean exists = false;
		for (Orderline lineItem: this.orderLines) {
			if (lineItem.getProduct().getId() == orderLine.getProduct().getId()) {
				lineItem.setQuantity(orderLine.getQuantity() + 1);
				exists = true;
			}
		}
		if (!exists) {
			this.orderLines.add(orderLine);
		}
		
	}
	
	public void removeOrderLine(int id) {
		Orderline removedOrderLine = null;
		for (Orderline orderline : orderLines) {
			if (id == orderline.getProduct().getId()) {
				removedOrderLine = orderline;
			}
		}
		if (removedOrderLine != null) {
			removeOrderLine(removedOrderLine);
		}
		
	}

	public void removeOrderLine(Orderline orderLine) {
		this.orderLines.remove(orderLine);
		orderLine.setOrder(null);
	}

	public void clearOrderLines() {
		for (Orderline orderline : orderLines) {
			orderline.setOrder(null);
			orderline.setProduct(null);
		}
		orderLines.clear();
	}

	public void setOrderLines(List<Orderline> orderLines) {
		this.orderLines = orderLines;
	}

}
