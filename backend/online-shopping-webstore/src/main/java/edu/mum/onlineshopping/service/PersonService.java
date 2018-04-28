package edu.mum.onlineshopping.service;

import java.util.List;

import edu.mum.onlineshopping.domain.Person;

public interface PersonService {

	Person savePerson(Person person);

	List<Person> findByEmail(String email);

	List<Person> getAllPerson();

	Person findById(Long id);

	void removePerson(Person person);

}
