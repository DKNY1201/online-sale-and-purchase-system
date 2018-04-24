package edu.mum.pm.domain;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderTable")
public class CustomerOrder {

    @Id
    @GeneratedValue
    private int id;
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
    @OneToOne
    private Customer customer;

    public int getId() {
        return id;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        int quantity = 0;
        for (OrderDetail ol : this.orderDetails) {
            quantity += ol.getQuantity();
        }
        return quantity;
    }

    public double getTotalAmount() {
        double totalAmount = 0;

        for (OrderDetail ol : this.orderDetails) {
            totalAmount += ol.getSubtotal();
        }
        return totalAmount;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setCustomerOrder(this);
        boolean exists = false;
        for (OrderDetail lineItem : this.orderDetails) {
            if (lineItem.getProduct().getId() == orderDetail.getProduct().getId()) {
                lineItem.setQuantity(orderDetail.getQuantity() + 1);
                exists = true;
            }
        }
        if (!exists) {
            this.orderDetails.add(orderDetail);
        }

    }

    public void removeOrderDetail(int id) {
        OrderDetail removedOrderDetail = null;
        for (OrderDetail orderDetail : orderDetails) {
            if (id == orderDetail.getProduct().getId()) {
                removedOrderDetail = orderDetail;
            }
        }
        if (removedOrderDetail != null) {
            removeOrderDetail(removedOrderDetail);
        }

    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.remove(orderDetail);
        orderDetail.setCustomerOrder(null);
    }

    public void clearOrderDetails() {
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setCustomerOrder(null);
            orderDetail.setProduct(null);
        }
        orderDetails.clear();
    }

}
