package edu.mum.pm.controller;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.Role;
import edu.mum.pm.domain.User;
import edu.mum.pm.service.PersonService;
import edu.mum.pm.service.RoleService;
import edu.mum.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * User Management Controller
 *
 * @author DatDoan
 */
@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", personService.getAllPerson());
        return "admin/user/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute("user") Customer customer,
                      @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Customer updatedCustomer = personService.findById(id);
            User user = userService.findByEmail(updatedCustomer.getEmail());
            if (user.getRoles().size() == 1) {
                updatedCustomer.setRole(user.getRoles().get(0).getId());
            }
            model.addAttribute("user", updatedCustomer);
        }
        return "admin/user/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("user") Customer customer) {
        String view = "redirect:/admin/user/";
        User user = userService.findByEmail(customer.getEmail());
        if (customer.getId() != 0) {
            if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
                customer.setPassword(encoder.encode(customer.getPassword()));
            } else {
                customer.setPassword(user.getPassword());
            }
        } else {
            if (user != null) {
                model.addAttribute("errorMsg", "This email already exists. Please use another email.");
                view = "admin/user/create";
                return view;
            }
            customer.setPassword(encoder.encode(customer.getPassword()));
        }
        personService.savePerson(customer);
        return view;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id) {
        Customer customer = personService.findById(id);
        personService.removePerson(customer);
        ;
        return "redirect:/admin/user/";
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.getAll();
    }
}
