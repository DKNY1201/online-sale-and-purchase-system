package edu.mum.pm.service;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.Role;
import edu.mum.pm.domain.User;
import edu.mum.pm.repository.PersonRepository;
import edu.mum.pm.repository.RoleRepository;
import edu.mum.pm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Customer savePerson(Customer customer) {
        List<User> users = userRepository.findByEmailAllIgnoreCase(customer.getEmail());
        User user = null;
        if (users.size() == 1) {
            user = users.get(0);
        } else {
            user = new User();
        }
        Role role = roleRepository.findOne(customer.getRole());
        user.setEmail(customer.getEmail());
        user.setPassword(customer.getPassword());
        user.addRole(role);
        user.setEnabled(customer.isEnable());
        userRepository.save(user);

        return personRepository.save(customer);
    }

    public List<Customer> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Customer> getAllPerson() {
        return personRepository.findByEnable(true);
    }

    public Customer findById(Long id) {
        return personRepository.findOne(id);
    }

    public void removePerson(Customer customer) {
        List<User> users = userRepository.findByEmailAllIgnoreCase(customer.getEmail());
        User user = null;
        if (users.size() == 1) {
            user = users.get(0);
        } else {
            user = new User();
        }
        user.clearRoles();
        customer.setAddress(null);
        userRepository.delete(user);
        personRepository.delete(customer);
    }

}
