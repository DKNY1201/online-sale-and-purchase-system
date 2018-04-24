package edu.mum.pm.controller;

import edu.mum.pm.domain.Product;
import edu.mum.pm.domain.ProductType;
import edu.mum.pm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public String findByProductType(@PathVariable("type") String type, Model model) {
        ProductType productType = ProductType.valueOf(type.toUpperCase());
        List<Product> products = service.findByProductType(productType);
        model.addAttribute("products", products);
        return "index";
    }

}
