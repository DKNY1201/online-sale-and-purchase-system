package edu.mum.onlineshopping.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.service.PersonService;

@Component
public class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
	HttpSession session;
	
	@Autowired
	PersonService service;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		System.out.println(event.getAuthentication().getName() + " logged successully");
		List<Person> persons = service.findByEmail(event.getAuthentication().getName());
		if (persons.size() == 1) {
			session.setAttribute("loggedUser", persons.get(0));
		}
	}

}
