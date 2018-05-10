package edu.mum.onlineshopping.controller;

import java.util.Arrays;
import java.util.List;

import edu.mum.onlineshopping.config.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;
import edu.mum.onlineshopping.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductManagementController {
	
	@Autowired
	private ProductService service;

	@Autowired
	private SessionListener session;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", service.getAllProduct());
		return "admin/product/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("product") Product product,
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			model.addAttribute("product", service.getProduct(id));
		}
		return "admin/product/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("product") Product product) {
		product.setPerson(session.getPerson());
		service.save(product);
		return "redirect:/admin/product/";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String delete(@PathVariable("id") int id) {
		Product product = service.getProduct(id);
		service.delete(product);
		return "redirect:/admin/product/";
	}
	
	@ModelAttribute("productTypes")
	public List<ProductType> productTypes() {
		return Arrays.asList(ProductType.values());
	}
}
