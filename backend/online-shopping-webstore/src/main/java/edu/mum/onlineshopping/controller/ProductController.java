package edu.mum.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;
import edu.mum.onlineshopping.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@RequestMapping(value="/type/{type}", method=RequestMethod.GET)
	public String findByProductType(@PathVariable("type") String type, Model model) {
		ProductType productType = ProductType.valueOf(type.toUpperCase());
		List<Product> products = service.findByProductType(productType);
		model.addAttribute("products", products);
		return "index";
	}

	@RequestMapping(value="/type/all", method=RequestMethod.GET)
	public String findAllProduct(Model model) {
		List<Product> products = service.findAllProduct();
		model.addAttribute("products", products);
		return "index";
	}
	
}
