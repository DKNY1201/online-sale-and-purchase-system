package mum.edu.pm.service;

import mum.edu.pm.entity.CustomerOrder;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderService {
    CustomerOrder addOrder(CustomerOrder order);

    List<CustomerOrder> getAllOrders();

    Optional<CustomerOrder> getOrder(long orderId);

    void removeOrder(CustomerOrder order);
}
