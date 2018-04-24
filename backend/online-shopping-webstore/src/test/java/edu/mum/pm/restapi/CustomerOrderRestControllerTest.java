package edu.mum.pm.restapi;

import edu.mum.pm.OnlineStoreApplication;
import edu.mum.pm.domain.Customer;
import edu.mum.pm.domain.CustomerOrder;
import edu.mum.pm.domain.OrderDetail;
import edu.mum.pm.domain.Product;
import edu.mum.pm.domain.ProductType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerOrderRestControllerTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8080/api/product";

    @Before
    public void setup() {
        url = "http://localhost:" + port;
    }

    @Test
    public void test() {
        // Create a new product for the testing purpose
        Product product = new Product("test", "test", 11, ProductType.BREAKFAST);
        Product savedProduct = restTemplate.postForObject(url + "/api/product/add", product, Product.class);
        if (!savedProduct.getProductName().equals(product.getProductName())) {
            fail("Saved Product fail");
        }

        Customer[] customers = restTemplate.getForObject(url + "/api/person/list", Customer[].class);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customers[0]);
        OrderDetail orderLine = new OrderDetail();
//		orderLine.setCustomerOrder(customerOrder);
        orderLine.setProduct(savedProduct);
        orderLine.setQuantity(1);
        customerOrder.setOrderDate(new Date());
        customerOrder.addOrderDetail(orderLine);

        // Creating a new customerOrder.
        CustomerOrder savedCustomerOrder = restTemplate.postForObject(url + "/api/customerOrder/add", customerOrder, CustomerOrder.class);
        if (savedCustomerOrder == null || savedCustomerOrder.getId() == 0) {
            fail("Saved CustomerOrder fail");
        }

        // deleting the CustomerOrder
        CustomerOrder deletedCustomerOrder = restTemplate.postForObject(url + "/api/customerOrder/delete/" + savedCustomerOrder.getId(), null, CustomerOrder.class);
        if (deletedCustomerOrder.getId() != savedCustomerOrder.getId()) {
            fail("Deleted CustomerOrder fail");
        }

        // deleting the product
        Product delete = restTemplate.postForObject(url + "/api/product/delete/" + savedProduct.getId(), null, Product.class);
        if (delete.getId() != savedProduct.getId()) {
            fail("Deleted Product fail");
        }

        // listing the CustomerOrder
        CustomerOrder[] customerOrders = restTemplate.getForObject(url + "/api/customerOrder/list", CustomerOrder[].class);
        // check the existing of the deleted product

        for (CustomerOrder o : customerOrders) {
            if (o.getId() == savedCustomerOrder.getId()) {
                fail("Listed Product fail");
                break;
            }
        }

    }


}
