package edu.mum.onlineshopping.restapi;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import edu.mum.onlineshopping.OnlineShoppingApplication;
import edu.mum.onlineshopping.domain.Order;
import edu.mum.onlineshopping.domain.Orderline;
import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderRestControllerTest {
	
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
		Product product = new Product("test", "test", 11, ProductType.FASHION, "https://ss7.vzw.com/is/image/VerizonWireless/iphone7-front-matblk?$png8alpha256$&hei=410");
		Product savedProduct = restTemplate.postForObject(url + "/api/product/add", product, Product.class);
		if (!savedProduct.getProductName().equals(product.getProductName())) {
			fail("Saved Product fail");
		}
		
		Person[] persons = restTemplate.getForObject(url + "/api/person/list", Person[].class);
		
		Order order = new Order();
		order.setPerson(persons[0]);
		Orderline orderLine = new Orderline();
//		orderLine.setOrder(order);
		orderLine.setProduct(savedProduct);
		orderLine.setQuantity(1);
		order.setOrderDate(new Date());
		order.addOrderLine(orderLine);
		
		// Creating a new order.
		Order savedOrder = restTemplate.postForObject(url + "/api/order/add", order, Order.class);
		if (savedOrder == null || savedOrder.getId() == 0) {
			fail("Saved Order fail");
		}
		
		// deleting the Order
		Order deletedOrder = restTemplate.postForObject(url + "/api/order/delete/" + savedOrder.getId(), null, Order.class);
		if (deletedOrder.getId() != savedOrder.getId()) {
			fail("Deleted Order fail");
		}

		// deleting the product
		Product delete = restTemplate.postForObject(url + "/api/product/delete/" + savedProduct.getId(), null, Product.class);
		if (delete.getId() != savedProduct.getId()) {
			fail("Deleted Product fail");
		}
		
		// listing the Order
		Order[] orders = restTemplate.getForObject(url + "/api/order/list", Order[].class);
		// check the existing of the deleted product
		
		for (Order o: orders) {
			if (o.getId() == savedOrder.getId()) {
				fail("Listed Product fail");
				break;
			}
		}
		
	}
	
	
	
}
