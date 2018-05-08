package edu.mum.onlineshopping.service;

import static org.junit.Assert.fail;

import java.util.List;

import edu.mum.onlineshopping.OnlineShoppingApplication;
import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	private Product[] testProducts;
	final static int NUMBER_OF_PRODUCTS = 10;

	@Before
	public void setUp() throws Exception {
		// Remove previous objects
		deleteTestObjects();

		// Creating objects to use during the test
		testProducts = new Product[NUMBER_OF_PRODUCTS];

		for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
			Product product = new Product();
			product.setProductName("TEST-productName" + i);
			product.setDescription("TEST-Item Description" + i);
			product.setPrice(i + 100.0);
			ProductType productType = null;
			switch (i % 3) {
				case 0:
					productType = ProductType.FASHION;
					break;
				case 1:
					productType = ProductType.ELECTRONIC;
					break;
				default:
					productType = ProductType.FURNITURE;
			}
			product.setProductType(productType);
			testProducts[i] = product;
		}

		// Store test objects in database -- Skipping 0 for Create Test
		for (int i = 1; i < NUMBER_OF_PRODUCTS; i++) {
			testProducts[i] = productService.save(testProducts[i]);
		}
	}

	@After
	public void tearDown() throws Exception {
		deleteTestObjects();
	}

	@Test
	public void testSave() {
		Product stored = productService.save(testProducts[0]);
		Product saved = productService.getProduct(stored.getId());
		if (!compareProduct(testProducts[0], saved)) {
			fail("Not storing or retrieving Product");
		}
	}

	@Test
	public void testGetProduct() {
		Product saved = productService.getProduct((testProducts[1].getId()));
		if (!compareProduct(testProducts[1], saved)) {
			fail("Not storing or retrieving Product");
		}
	}

	@Test
	public void testGetAllProduct() {
		List<Product> saved = productService.getAllProduct();
		if (saved.size() < NUMBER_OF_PRODUCTS - 1) {
			fail("Not storing or retrieving Products");
		}
	}

	@Test
	public void testFindByTextSearch() {
		List<Product> saved = productService.findByTextSearch(testProducts[2].getDescription());
		if (!compareProduct(saved, testProducts[2])) {
			fail("Search couldn't retrieve Product");
		}
	}

	@Test
	public void testFindByPrice() {
		double price = testProducts[3].getPrice();
		List<Product> saved = productService.findByPrice(price, price);
		if (!compareProduct(saved, testProducts[3])) {
			fail("Search couldn't retrieve Product");
		}
	}

	@Test
	public void testFindByProductType() {
		for (int i = 4; i < 7; i++) {
			List<Product> saved = productService.findByProductType(testProducts[i].getProductType());
			if (!compareProduct(saved, testProducts[i])) {
				fail("Search couldn't retrieve Product by Type" + testProducts[i].getProductType());
			}
		}
	}

	private void deleteTestObjects() {
		for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
			try {
				List<Product> products = productService.findByTextSearch(testProducts[i].getProductName());
				for (Product product : products) {
					productService.delete(product);
				}
			} catch (Exception e) {
				// Do not log exceptions
			}
		}
	}

	private boolean compareProduct(Product a, Product b) {
		if ((a == null) || (b == null)) {
			return false;
		}
		if (!a.getDescription().equals(b.getDescription())) {
			return false;
		}
		if (a.getPrice() != b.getPrice()) {
			return false;
		}
		if (!a.getProductName().equals(b.getProductName())) {
			return false;
		}
		if (!a.getProductType().equals(b.getProductType())) {
			return false;
		}
		return true;
	}

	private boolean compareProduct(List<Product> products, Product compare) {
		boolean found = false;
		for (Product product : products) {
			if (compareProduct(product, compare)) {
				found = true;
				break;
			}
		}
		return found;
	}

}
