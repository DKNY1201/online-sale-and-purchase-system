package edu.mum.pm.config;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component("sessionListener")
public class SessionListener {

    @Autowired
    PersonService service;

    @Autowired
    HttpSession session;

    public Customer getPerson() {
        if (session.getAttribute("loggedUser") != null) {
            return (Customer) session.getAttribute("loggedUser");
        }
        List<Customer> customers = service.findByEmail(getPrincipal());
        if (customers.size() == 1) {
            session.setAttribute("loggedUser", customers.get(0));
            return customers.get(0);
        }
        return new Customer();

    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}


