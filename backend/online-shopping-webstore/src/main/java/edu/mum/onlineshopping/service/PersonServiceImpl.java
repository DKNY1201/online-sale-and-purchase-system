package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Role;
import edu.mum.onlineshopping.domain.User;
import edu.mum.onlineshopping.repository.PersonRepository;
import edu.mum.onlineshopping.repository.RoleRepository;
import edu.mum.onlineshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Person savePerson(Person person) {
		List<User> users = userRepository.findByEmailAllIgnoreCase(person.getEmail());
		User user = null;
		if (users.size() == 1) {
			user = users.get(0);
		} else {
			user = new User();
		}
		Role role = roleRepository.findOne(person.getRole()); 
		user.setEmail(person.getEmail());
		user.setPassword(person.getPassword());
		user.addRole(role);
		user.setEnabled(person.isEnable());
		userRepository.save(user);
		
		return personRepository.save(person);
	}

	public List<Person> findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public List<Person> getAllPerson() {
		return personRepository.findByEnable(true);
	}

	public Person findById(Long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		List<User> users = userRepository.findByEmailAllIgnoreCase(person.getEmail());
		User user = null;
		if (users.size() == 1) {
			user = users.get(0);
		} else {
			user = new User();
		}
		user.clearRoles();
		person.setAddress(null);
		userRepository.delete(user);
		personRepository.delete(person);
	}

}
