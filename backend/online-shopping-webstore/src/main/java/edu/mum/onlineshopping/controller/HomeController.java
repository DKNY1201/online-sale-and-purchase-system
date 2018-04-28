package edu.mum.onlineshopping.controller;

import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private ProductService service;

	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		List<Product> products = service.findAllProduct();
		model.addAttribute("products", products);
		return "index";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	
}
