package edu.mum.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Role;
import edu.mum.onlineshopping.domain.User;
import edu.mum.onlineshopping.service.PersonService;
import edu.mum.onlineshopping.service.RoleService;
import edu.mum.onlineshopping.service.UserService;

/**
 * User Management Controller
 * @author DatDoan
 *
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
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", personService.getAllPerson());
		return "admin/user/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("user") Person person,
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			Person updatedPerson = personService.findById(id);
			User user = userService.findByEmail(updatedPerson.getEmail());
			if (user.getRoles().size() == 1) {
				updatedPerson.setRole(user.getRoles().get(0).getId());
			}
			model.addAttribute("user", updatedPerson);
		}
		return "admin/user/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("user") Person person) {
		String view = "redirect:/admin/user/";
		User user = userService.findByEmail(person.getEmail());
		if (person.getId() != 0) {
			if (person.getPassword() != null && !person.getPassword().isEmpty()) {
				person.setPassword(encoder.encode(person.getPassword()));
			} else {
				person.setPassword(user.getPassword());
			}
		} else {
			if (user != null) {
				model.addAttribute("errorMsg", "This email already exists. Please use another email.");
				view = "admin/user/create";
				return view;
			}
			person.setPassword(encoder.encode(person.getPassword()));
		}
		personService.savePerson(person);
		return view;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String delete(@PathVariable("id") Long id) {
		Person person = personService.findById(id);
		personService.removePerson(person);;
		return "redirect:/admin/user/";
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles() {
		return roleService.getAll();
	}
}
