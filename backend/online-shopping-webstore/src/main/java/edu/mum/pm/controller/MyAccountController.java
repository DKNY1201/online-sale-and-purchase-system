package edu.mum.pm.controller;

import edu.mum.pm.config.SessionListener;
import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.User;
import edu.mum.pm.service.OrderService;
import edu.mum.pm.service.PersonService;
import edu.mum.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping({"order"})
    public String order(Model model) {
        Customer customer = sessionListener.getPerson();
        model.addAttribute("historicalOrder", orderService.findByPerson(customer));
        return "myaccount/order";
    }

    // Update Account
    @GetMapping({"/account/update"})
    public String account(Model model) {
        Customer updatedCustomer = personService.findById(sessionListener.getPerson().getId());
        User user = userService.findByEmail(updatedCustomer.getEmail());
        if (user.getRoles().size() == 1) {
            updatedCustomer.setRole(user.getRoles().get(0).getId());
        }
        model.addAttribute("user", updatedCustomer);
        return "myaccount/account";
    }

    @PostMapping("/acoount/update")
    public String updateAccount(Model model, @ModelAttribute("user") Customer customer) {
        User user = userService.findByEmail(customer.getEmail());
        if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
            customer.setPassword(encoder.encode(customer.getPassword()));
        } else {
            customer.setPassword(user.getPassword());
        }
        personService.savePerson(customer);
        return "redirect:/";
    }

    // Sign up
    @GetMapping({"/account/signup"})
    public String signUp(Model model, @ModelAttribute("user") Customer customer) {
        // set the default role for a new user
        customer.setRole(2);
        customer.setEnable(true);
        return "myaccount/signup";
    }

    @PostMapping("/account/signup")
    public String createNewAccount(Model model, @ModelAttribute("user") Customer customer) {
        String view = "myaccount/signup";
        User user = userService.findByEmail(customer.getEmail());
        if (user != null) {
            model.addAttribute("errorMsg", "This email already exists. Please use another email.");
            return view;
        }
        if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
            customer.setPassword(encoder.encode(customer.getPassword()));
        }
        personService.savePerson(customer);
        model.addAttribute("infoMsg", "Your new account has been created sucessfully. Click here to login");
        return view;
    }

}
