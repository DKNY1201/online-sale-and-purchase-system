package edu.mum.pm.service;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.CustomerOrder;
import edu.mum.pm.domain.Product;
import edu.mum.pm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public CustomerOrder save(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    public void delete(CustomerOrder customerOrder) {
        orderRepository.delete(customerOrder);
    }

    public List<CustomerOrder> findByProduct(Product product) {
        return orderRepository.findDistinctOrderByOrderDetails_Product(product);
    }

    public List<CustomerOrder> findByPerson(Customer customer) {
        return orderRepository.findOrderByPerson(customer);
    }

    public List<CustomerOrder> findByDate(Date minDate, Date maxDate) {
        return orderRepository.findOrderByOrderDateBetween(minDate, maxDate);
    }

    public CustomerOrder findById(int id) {
        return orderRepository.findOne(id);
    }

    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

}
