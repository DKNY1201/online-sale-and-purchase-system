package edu.mum.onlineshopping.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.service.PersonService;

@RestController()
@RequestMapping("/api/person")
public class PersonRestController {
	
	@Autowired
	private PersonService service;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Person person) {
		return new ResponseEntity<Person>(service.savePerson(person), HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		return new ResponseEntity<List<Person>>(service.getAllPerson(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		Person person = service.findById(id);
		service.removePerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody Person person) {
		return new ResponseEntity<Person>(service.savePerson(person), HttpStatus.OK);
	}
}
