package edu.mum.onlineshopping.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import edu.mum.onlineshopping.config.SessionListener;
import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.domain.Payment;
import edu.mum.onlineshopping.domain.PaymentType;
import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.service.OrderService;
import edu.mum.onlineshopping.service.PaymentService;
import edu.mum.onlineshopping.service.ProductService;

@Controller
@RequestMapping("/cart")
@SessionAttributes({"myOrder"})
public class OrderController {
	@Autowired
	public EmailService emailService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SessionListener session;
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String myCart(ModelMap map, Model model, @ModelAttribute Order order) {
		Order myOrder = this.getCurrentOrder(map);
//		Person person = session.getPerson();
////		if (person != null) {
////			myOrder.setCustomerName(person.getFirstName() + " " + person.getLastName());
////			myOrder.setShippingAddress(person.getAddress().getCity() + ", " + person.getAddress().getState()
////					+ ", " + person.getAddress().getStreet());
////		}
		addOrderToSession(map, myOrder);
		return "cart/index";
	}

	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public String checkout(ModelMap map, @ModelAttribute("myOrder") Order order, Model model) {
		Order myOrder = this.getCurrentOrder(map);
		addOrderToSession(map, myOrder);

		double totalPrice = 0;

		for (int i = 0; i < myOrder.getOrderLines().size(); i++) {
			totalPrice += myOrder.getOrderLines().get(i).getProduct().getPrice() * myOrder.getOrderLines().get(i).getQuantity();
		}

		model.addAttribute("totalPrice", totalPrice);

		return "cart/checkout";
	}

	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String update_checkout(ModelMap map, @ModelAttribute("myOrder") Order order, HttpServletRequest request) {
		MultiValueMap<String, String> mapValue = new LinkedMultiValueMap<String, String>();
		RestTemplate rest = new RestTemplate();
		String view = "cart/checkout";
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
		        request.getContextPath() + "/api/payment/check";

		String cardNumber = request.getParameter("cardNumber");
		String expiryYear = request.getParameter("expiryYear");
		String expiryMonth = request.getParameter("expiryMonth");
		String cardHolderName = request.getParameter("cardHolderName");
		String ccv = request.getParameter("cvCode");
		mapValue.add("cardNumber", cardNumber);
		mapValue.add("expiryYear", expiryYear);
		mapValue.add("expiryMonth", expiryMonth);
		mapValue.add("cardHolderName", cardHolderName);
		mapValue.add("ccv", ccv);
		mapValue.add("paymentAmount",Double.toString(order.getTotalAmount()));
		String checkPaymentResult = rest.postForObject(baseUrl, mapValue, String.class);
		if (!checkPaymentResult.equals("Success")) {
			map.addAttribute("order", order);
			map.addAttribute("totalPrice", Double.toString(order.getTotalAmount()));
			map.addAttribute("errorMsg", checkPaymentResult);
			return view;
		}
		Order myOrder = getCurrentOrder(map);
		myOrder.setShippingAddress(order.getShippingAddress());
		myOrder.setBillingAddress(order.getBillingAddress());
		
		return "redirect:/cart/submit";
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
		
		//Save payment for vendor
		List<Orderline> orderLineList = myOrder.getOrderLines();
		
		for (Orderline orderline : orderLineList) {
			Payment payment = new Payment();
			payment.setOrder(myOrder);
			payment.setPerson(orderline.getProduct().getPerson());
			payment.setTotalValue(orderline.getSubtotal() * 0.8);
			payment.setPaymentType(PaymentType.SOLD);
			paymentService.save(payment);
		}
		
		
		// Save payment for admin
		Payment payment1 = new Payment();
		payment1.setOrder(myOrder);
		payment1.setTotalValue(myOrder.getTotalAmount() * 0.2);
		payment1.setPaymentType(PaymentType.COMMISSION);
		paymentService.save(payment1);

		// Save payment for customer
		Payment payment2 = new Payment();
		payment2.setOrder(myOrder);
		payment2.setPerson(session.getPerson());
		payment2.setTotalValue(myOrder.getTotalAmount());
		payment2.setPaymentType(PaymentType.BOUGHT);
		paymentService.save(payment2);

		// Clear the order session
		map.addAttribute("myOrder", new Order());
		emailService.sendOrderMessageUsingTemplate(myOrder);
		return "redirect:/me/order";
	}
	
}
