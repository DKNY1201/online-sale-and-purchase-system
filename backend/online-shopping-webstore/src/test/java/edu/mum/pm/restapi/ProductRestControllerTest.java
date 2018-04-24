package edu.mum.pm.restapi;

import edu.mum.pm.OnlineStoreApplication;
import edu.mum.pm.domain.Product;
import edu.mum.pm.domain.ProductType;
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
public class ProductRestControllerTest {

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
        // test the product saving function
        Product product = new Product("test", "test", 11, ProductType.BREAKFAST);
        Product save = restTemplate.postForObject(url + "/api/product/add", product, Product.class);
        if (!save.getProductName().equals(product.getProductName())) {
            fail("Saved Product fail");
        }

        // Test updating the product.
        save.setDescription("Updated Description");
        Product update = restTemplate.postForObject(url + "/api/product/update", save, Product.class);
        if (!update.getDescription().equals(save.getDescription())) {
            fail("Updated Product fail");
        }

        // Test deleting the product
        Product delete = restTemplate.postForObject(url + "/api/product/delete/" + save.getId(), null, Product.class);
        if (delete.getId() != save.getId()) {
            fail("Deleted Product fail");
        }

        // Testing listing the product
        Product[] products = restTemplate.getForObject(url + "/api/product/list", Product[].class);
        // check the existing of the deleted product

        for (Product p : products) {
            if (p.getId() == save.getId()) {
                fail("Listed Product fail");
                break;
            }
        }

    }


}
