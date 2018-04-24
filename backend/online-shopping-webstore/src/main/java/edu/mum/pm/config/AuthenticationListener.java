package edu.mum.pm.config;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    HttpSession session;

    @Autowired
    PersonService service;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        System.out.println(event.getAuthentication().getName() + " logged successully");
        List<Customer> customers = service.findByEmail(event.getAuthentication().getName());
        if (customers.size() == 1) {
            session.setAttribute("loggedUser", customers.get(0));
        }
    }

}
