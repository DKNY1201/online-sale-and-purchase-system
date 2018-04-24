package edu.mum.pm.repository;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.CustomerOrder;
import edu.mum.pm.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {

    List<CustomerOrder> findDistinctOrderByOrderDetails_Product(Product product);

    List<CustomerOrder> findOrderByPerson(Customer customer);

    List<CustomerOrder> findOrderByOrderDateBetween(Date minDate, Date maxDate);


}
