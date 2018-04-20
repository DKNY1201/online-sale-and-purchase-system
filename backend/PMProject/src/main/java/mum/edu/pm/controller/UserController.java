package mum.edu.pm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mum.edu.pm.entity.*;
import mum.edu.pm.service.*;

@RestController
@RequestMapping("/users") 
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/all") 
	public Map<String, User> getAll() {
		return userService.getAll();
	}
}