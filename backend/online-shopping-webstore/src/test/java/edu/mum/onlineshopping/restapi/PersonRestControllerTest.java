package edu.mum.onlineshopping.restapi;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import edu.mum.onlineshopping.OnlineShoppingApplication;
import edu.mum.onlineshopping.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonRestControllerTest {
	
	@LocalServerPort
	private int port;
	
	private RestTemplate restTemplate = new RestTemplate();
	private String url = "http://localhost:8080/api/person";
	
	@Before
	public void setup() {
		url = "http://localhost:" + port;
	}
	
	@Test
	public void test() {
		// test the person saving function
		Person person = new Person();
		person.setFirstName("First Name");
		person.setLastName("Last Name");
		person.setEmail("save@mum.edu");
		person.setPassword("123456");
		person.setRole(2);
		Person save = restTemplate.postForObject(url + "/api/person/add", person, Person.class);
		if (!save.getEmail().equals(person.getEmail())) {
			fail("Saved Person fail");
		}
		
		// Test updating the person.
		save.setFirstName("Updated First Name");
		Person update = restTemplate.postForObject(url + "/api/person/add", save, Person.class);
		if (!update.getFirstName().equals(save.getFirstName())) {
			fail("Updated Person fail");
		}

		// Test deleting the person
		Person delete = restTemplate.postForObject(url + "/api/person/delete/" + save.getId(), null, Person.class);
		if (delete.getId() != save.getId()) {
			fail("Deleted Person fail");
		}
		
		// Testing listing the person
		Person[] persons = restTemplate.getForObject(url + "/api/person/list", Person[].class);
		// check the existing of the deleted person
		
		for (Person p: persons) {
			if (p.getId() == save.getId()) {
				fail("Listed Person fail");
				break;
			}
		}
		
	}
	
	
	
}
