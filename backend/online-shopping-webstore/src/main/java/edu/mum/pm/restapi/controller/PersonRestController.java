package edu.mum.pm.restapi.controller;

import edu.mum.pm.domain.Customer;
import edu.mum.pm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/person")
public class PersonRestController {

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(service.savePerson(customer), HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return new ResponseEntity<List<Customer>>(service.getAllPerson(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Customer customer = service.findById(id);
        service.removePerson(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(service.savePerson(customer), HttpStatus.OK);
    }
}
