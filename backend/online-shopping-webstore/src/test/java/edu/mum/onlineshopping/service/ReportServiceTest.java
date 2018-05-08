package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.OnlineShoppingApplication;
import edu.mum.onlineshopping.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportServiceTest {

	@Autowired
	private EmailService emailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private RoleService roleService;

	private Order[] testOrder;
	private Product[] testProduct;
	private Person[] testPerson;
	private Role[] testRole;

	final static int TEST_NumberOfOrders = 5;
	final static int TEST_NumberOfOrderLines = 7;
	final static int TEST_NumberOfProducts = 20;
	final static int TEST_NumberOfPersons = 3;
	final static int TEST_NumberOfRoles = 3;

	@Before
	public void setUp() throws Exception {
		// Remove previous objects
		deleteTestObjects();
// Related objects
		createRoles();
		createPersons();
		createProducts();

		// Creating objects to use during the test
		testOrder = new Order[TEST_NumberOfOrders];

		int countPerson = 0;
		int countProduct = 0;

		for (int i = 0; i < TEST_NumberOfOrders; i++) {
			Order order = new Order();
			order.setId(i+1);
			order.setOrderDate(new Date());
			order.setPerson(testPerson[countPerson++ % TEST_NumberOfPersons]);

			for (int l = 0; l < TEST_NumberOfOrderLines; l++) {
				Orderline line = new Orderline();
				line.setProduct(testProduct[countProduct++ % TEST_NumberOfProducts]);
				line.setQuantity(countProduct);
				order.addOrderLine(line);
			}
			testOrder[i] = order;
		}

		// Store test objects in database -- Skip 0 for Create Test
		/*for (int i = 1; i < TEST_NumberOfOrders; i++) {
			testOrder[i] = orderService.save(testOrder[i]);
		}
		testOrderList = orderService.findAll();*/
	}

	@After
	public void tearDown() throws Exception {
		deleteTestObjects();
	}

	@Test
	public void testGenerateReport() {
		Report report = new Report();
		report.setDateTo("");
		report.setDateFrom("");
		report.setEmail("ducvo1983@gmail.com");
		report.setOrders(Arrays.asList(testOrder));
		reportService.generateReport(report);
		assertTrue(null != report.getReportExcelFile());
		// Test sending report
		String subject = "Test sending message with attachment";
		String message = "Test sending message with attachment";

		boolean ret = emailService.sendMessageWithAttachment(TinyEmailObject.buildEmailObject(report.getEmail(), subject, message), "TestSendingAttachment.xlsx", report.getReportExcelFile());
		assertTrue(ret);
		report.setOrders(null);
		reportService.generateReport(report);
		assertFalse(null != report.getReportExcelFile());
	}

	private void createRoles() {
		// Creating objects to use during the test
		testRole = new Role[TEST_NumberOfRoles];

		for (int i = 0; i < TEST_NumberOfRoles; i++) {
			Role role = new Role();
			role.setId(1);
			role.setName(i == 0 ? "ADMIN" : (i == 1 ? "USER" : "VENDOR"));
			testRole[i] = role;
		}
	}

	private void createPersons() {
		// Creating objects to use during the test
		testPerson = new Person[TEST_NumberOfPersons];
		int countRole = 0;
		for (int i = 0; i < TEST_NumberOfPersons; i++) {
			Person person = new Person();
			person.setId(i+1);
			person.setEmail(i + "test@email.com");
			person.setEnable((i % 2 == 0));
			person.setFirstName("firstName Test" + i);
			person.setLastName("lastName Test" + i);
			person.setPhone("111888777" + i);
			person.setAddress(new Address());
			person.getAddress().setZipcode("5527" + i);
			person.getAddress().setCity("Fairfield" + i);
			person.getAddress().setState("IA" + i);
			person.setRole(testRole[countRole++ % TEST_NumberOfRoles].getId());
			//person.getAddress().setCountry("USA" + i);
			testPerson[i] = person;
		}

		// Store test objects in database
		/*for (int i = 0; i < TEST_NumberOfPersons; i++) {
			testPerson[i] = personService.savePerson(testPerson[i]);
		}
		testPersonList = personService.getAllPerson();*/
	}

	private void createProducts() {
		testProduct = new Product[TEST_NumberOfProducts];

		for (int i = 0; i < TEST_NumberOfProducts; i++) {
			Product product = new Product();
			product.setId(i+1);
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
			testProduct[i] = product;
		}
		// Store test objects in database
		/*for (int i = 0; i < TEST_NumberOfProducts; i++) {
			testProduct[i] = productService.save(testProduct[i]);
		}
		testProductList = productService.getAllProduct();*/
	}

	private void deleteTestObjects() {
		for (int i = 0; i < TEST_NumberOfOrders; i++) {
			try {
				Order order = orderService.findById(testOrder[i].getId());
				orderService.delete(order);
			} catch (Exception e) {
				// Do not log exceptions
			}
		}
		for (int i = 0; i < TEST_NumberOfProducts; i++) {
			try {
				List<Product> products = productService.findByTextSearch(testProduct[i].getProductName());
				for (Product product : products) {
					productService.delete(product);
				}
			} catch (Exception e) {
				// Do not log exceptions
			}
		}
		for (int i = 0; i < TEST_NumberOfPersons; i++) {
			try {
				Person person = personService.findById(testPerson[i].getId());
				personService.removePerson(person);
			} catch (Exception e) {
				// Do not log exceptions
			}
		}
	}

	private boolean compareOrder(Order a, Order b) {
		if ((a == null) || (b == null)) {
			return false;
		}
		if (a.getPerson().getId() != b.getPerson().getId()) {
			return false;
		}
		if (a.getTotalAmount() != b.getTotalAmount()) {
			return false;
		}
		return true;
	}


}
