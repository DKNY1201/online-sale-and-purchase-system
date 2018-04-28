package edu.mum.onlineshopping.service;

import java.util.Date;
import java.util.List;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Product;

public interface OrderService {

	Order save(Order order);
	
	void delete(Order order);
	
	List<Order> findByProduct(Product product);
	
	List<Order> findByPerson(Person person);

	List<Order> findByDate(Date minDate, Date maxDate);

	Order findById(int id);

	List<Order> findAll();

}
