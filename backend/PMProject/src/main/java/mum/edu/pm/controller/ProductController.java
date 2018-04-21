package mum.edu.pm.controller;

import mum.edu.pm.entity.Product;
import mum.edu.pm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByName(@RequestParam("item_name") String item_name) {
        return productService.getProductByName(item_name);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long prodId,
                              @Valid @RequestBody Product prodDetails) {
        Product prod = productService.findById(prodId)
                .orElseThrow(() -> new RuntimeException(String.format("Product not found with id : '%s'", prodId)));

        prod.setProductCategory(prodDetails.getProductCategory());
        prod.setProductDescription(prodDetails.getProductDescription());
        prod.setProductImage(prodDetails.getProductImage());
        prod.setProductManufacturer(prodDetails.getProductManufacturer());
        prod.setProductName(prodDetails.getProductName());
        prod.setProductPrice(prodDetails.getProductPrice());
        prod.setUnitStock(prodDetails.getUnitStock());
        prod.setVendor(prodDetails.getVendor());

        Product updatedProd = productService.save(prod);
        return updatedProd;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long prodId) {
        Product prod = productService.findById(prodId)
                .orElseThrow(() -> new RuntimeException(String.format("Product not found with id : '%s'", prodId)));

        productService.delete(prod);

        return ResponseEntity.ok().build();
    }

}
