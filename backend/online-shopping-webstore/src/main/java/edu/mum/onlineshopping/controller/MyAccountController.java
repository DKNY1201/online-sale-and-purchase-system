package edu.mum.onlineshopping.controller;

import edu.mum.onlineshopping.service.EmailService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.mum.onlineshopping.config.SessionListener;
import edu.mum.onlineshopping.domain.Payment;
import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.User;
import edu.mum.onlineshopping.service.OrderService;
import edu.mum.onlineshopping.service.PaymentService;
import edu.mum.onlineshopping.service.PersonService;
import edu.mum.onlineshopping.service.UserService;

@Controller
@RequestMapping("/me/")
public class MyAccountController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionListener sessionListener;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping({"order"})
	public String order(Model model) {
		Person person = sessionListener.getPerson();
		model.addAttribute("historicalOrder",orderService.findByPerson(person));
		return "myaccount/order";
	}

	// Update Account
	@GetMapping({"/account/update"})
	public String account(Model model) {
		Person updatedPerson = personService.findById(sessionListener.getPerson().getId());
		User user = userService.findByEmail(updatedPerson.getEmail());
		if (user.getRoles().size() == 1) {
			updatedPerson.setRole(user.getRoles().get(0).getId());
		}
		model.addAttribute("user", updatedPerson);
		return "myaccount/account";
	}
	
	@PostMapping("/account/update")
	public String updateAccount(Model model, @ModelAttribute("user")  Person person) {
		User user = userService.findByEmail(person.getEmail());
		if (person.getPassword() != null && !person.getPassword().isEmpty()) {
			person.setPassword(encoder.encode(person.getPassword()));
		} else {
			person.setPassword(user.getPassword());
		}
		personService.savePerson(person);
		return "redirect:/";
	}
	
	// Sign up
	@GetMapping({"/account/signup"})
	public String signUp(Model model, @ModelAttribute("user")  Person person) {
		// set the default role for a new user
		person.setRole(2);
		person.setEnable(true);
		return "myaccount/signup";
	}
	
	@PostMapping("/account/signup")
	public String createNewAccount(Model model, @ModelAttribute("user")  Person person, HttpServletRequest request) {
		String view = "myaccount/signup";
		User user = userService.findByEmail(person.getEmail());
		if (user != null) {
			model.addAttribute("errorMsg", "This email already exists. Please use another email.");
			return view;
		}
		if (person.getPassword() != null && !person.getPassword().isEmpty()) {
			person.setPassword(encoder.encode(person.getPassword()));
		}
		if (person.getRole() == 3) {
			MultiValueMap<String, String> mapValue = new LinkedMultiValueMap<String, String>();
			RestTemplate rest = new RestTemplate();
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
			mapValue.add("paymentAmount",Double.toString(25000));
			String checkPaymentResult = rest.postForObject(baseUrl, mapValue, String.class);
			if (!checkPaymentResult.equals("Success")) {
				model.addAttribute("errorMsg", checkPaymentResult);
				return view;
			}
		}
		personService.savePerson(person);
		model.addAttribute("infoMsg", "Your new account has been created successfully. Click here to login");
		emailService.sendUserRegisterMessageUsingTemplate(person);
		return view;
	}

	// Sign up Vendor
	@GetMapping({"/account/signup_vendor"})
	public String signUpVendor(Model model, @ModelAttribute("user")  Person person, HttpServletRequest request) {
		// set the default role for a new user
		
		person.setRole(3);
		person.setEnable(true);
		return "myaccount/signup";
	}
}
