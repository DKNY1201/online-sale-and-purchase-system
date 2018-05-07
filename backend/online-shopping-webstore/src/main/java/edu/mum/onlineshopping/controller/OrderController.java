package edu.mum.onlineshopping.controller;

import java.util.Date;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.onlineshopping.config.SessionListener;
import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.service.OrderService;
import edu.mum.onlineshopping.service.ProductService;

@Controller
@RequestMapping("/cart")
@SessionAttributes({"myOrder"})
public class OrderController {
	@Autowired
	public EmailServiceImpl emailService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SessionListener session;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String myCart(ModelMap map, Model model, @ModelAttribute Order order) {
		Order myOrder = this.getCurrentOrder(map);
		Person person = session.getPerson();
		if (person != null) {
			myOrder.setCustomerName(person.getFirstName() + " " + person.getLastName());
			myOrder.setShippingAddress(person.getAddress().getCity() + ", " + person.getAddress().getState()
					+ ", " + person.getAddress().getCountry());
		}
		addOrderToSession(map, myOrder);
		return "cart/index";
	}
	
	/***
	 * Add product into my order
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/add/product/{id}", method=RequestMethod.POST)
	public String addProduct(@PathVariable("id") int id, ModelMap map, Model model, @ModelAttribute Order order, BindingResult result) {
		Order myOrder = this.getCurrentOrder(map);
		Product product = productService.getProduct(id);
		Orderline orderLine = new Orderline();
		orderLine.setProduct(product);
		orderLine.setOrder(myOrder);
		orderLine.setQuantity(1); // default
		myOrder.addOrderLine(orderLine);
		addOrderToSession(map, myOrder);
		return "redirect:/cart/";
	}
	
	@RequestMapping(value="/remove/product/{id}", method=RequestMethod.GET)
	public String removeProduct(@PathVariable("id") int id, ModelMap map, Model model) {
		Order myOrder = this.getCurrentOrder(map);
		myOrder.removeOrderLine(id);
		addOrderToSession(map, myOrder);
		return "redirect:/cart/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(ModelMap map, @ModelAttribute("myOrder") Order order) {
		Order myOrder = getCurrentOrder(map);
		// Update the new quantity for each product
		for (int i = 0; i < myOrder.getOrderLines().size(); i++) {
			myOrder.getOrderLines().get(i).setQuantity(order.getOrderLines().get(i).getQuantity());
		}
		return "redirect:/cart/";
	}
	
	public Order getCurrentOrder(ModelMap map) {
		Order myOrder = (Order)map.get("myOrder");
		if (myOrder == null) {
			myOrder = new Order();
			map.addAttribute("myOrder", myOrder);
		}
		return myOrder;
	}
	
	public void addOrderToSession(ModelMap map, Order order) {
		map.addAttribute("myOrder", order);
	}
	
//	@RequestMapping(value="/submit", method=RequestMethod.GET)
//	public String getSubmit(ModelMap map, @ModelAttribute Order order) {
//		Order myOrder = this.getCurrentOrder(map);
//		addOrderToSession(map, myOrder);
//		return "/cart/index";
//	}
//	
	@RequestMapping(value="/submit", method=RequestMethod.GET)
	public String submit(ModelMap map, @ModelAttribute("myOrder") Order order) {
		Order myOrder = getCurrentOrder(map);
		// Update the new quantity for each product
		for (int i = 0; i < myOrder.getOrderLines().size(); i++) {
			myOrder.getOrderLines().get(i).setQuantity(order.getOrderLines().get(i).getQuantity());
		}
		myOrder.setOrderDate(new Date());
		myOrder.setPerson(session.getPerson());
		orderService.save(myOrder);

		// Clear the order session
		map.addAttribute("myOrder", new Order());
		emailService.sendOrderMessageUsingTemplate(myOrder);
		return "redirect:/me/order";
	}
	
	
}
