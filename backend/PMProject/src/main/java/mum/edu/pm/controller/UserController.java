package mum.edu.pm.controller;

import mum.edu.pm.entity.User;
import mum.edu.pm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/all")
    public Map<String, User> getAll() {
        return userService.getAll();
    }
}