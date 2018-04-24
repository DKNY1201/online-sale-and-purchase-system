package edu.mum.pm.controller;

import edu.mum.pm.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("person") Customer customer) {
        return "login";
    }
}
