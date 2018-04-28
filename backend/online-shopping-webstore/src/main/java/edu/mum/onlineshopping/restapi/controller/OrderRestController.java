package edu.mum.onlineshopping.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.service.OrderService;

@RestController()
@RequestMapping("/api/order")
public class OrderRestController {
	
	@Autowired
	private OrderService service;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Order order) {
		for (Orderline orderLine: order.getOrderLines()) {
			orderLine.setOrder(order);
		}
		return new ResponseEntity<Order>(service.save(order), HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		return new ResponseEntity<List<Order>>(service.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Order order = service.findById(id);
		order.clearOrderLines();
		service.delete(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
}
