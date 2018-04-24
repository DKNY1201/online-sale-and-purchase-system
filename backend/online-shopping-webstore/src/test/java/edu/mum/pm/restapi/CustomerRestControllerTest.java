package edu.mum.pm.restapi;

import edu.mum.pm.OnlineStoreApplication;
import edu.mum.pm.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRestControllerTest {

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
        // test the customer saving function
        Customer customer = new Customer();
        customer.setFirstName("First Name");
        customer.setLastName("Last Name");
        customer.setEmail("save@mum.edu");
        customer.setPassword("123456");
        customer.setRole(2);
        Customer save = restTemplate.postForObject(url + "/api/customer/add", customer, Customer.class);
        if (!save.getEmail().equals(customer.getEmail())) {
            fail("Saved Customer fail");
        }

        // Test updating the customer.
        save.setFirstName("Updated First Name");
        Customer update = restTemplate.postForObject(url + "/api/customer/add", save, Customer.class);
        if (!update.getFirstName().equals(save.getFirstName())) {
            fail("Updated Customer fail");
        }

        // Test deleting the customer
        Customer delete = restTemplate.postForObject(url + "/api/customer/delete/" + save.getId(), null, Customer.class);
        if (delete.getId() != save.getId()) {
            fail("Deleted Customer fail");
        }

        // Testing listing the customer
        Customer[] customers = restTemplate.getForObject(url + "/api/customer/list", Customer[].class);
        // check the existing of the deleted customer

        for (Customer p : customers) {
            if (p.getId() == save.getId()) {
                fail("Listed Customer fail");
                break;
            }
        }

    }


}
