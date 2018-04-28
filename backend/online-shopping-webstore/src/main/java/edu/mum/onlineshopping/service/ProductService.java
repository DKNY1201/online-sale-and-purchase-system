package edu.mum.onlineshopping.service;

import java.util.List;

import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;

public interface ProductService   {
	
	Product save(Product product);

	void delete(Product product);

	Product getProduct(int productId);

	List<Product> getAllProduct();
	
	List<Product> findByTextSearch(String criteria);

	List<Product> findByPrice(double minPrice, double maxPrice);
	
	List<Product> findByProductType(ProductType productType);

	List<Product> findAllProduct();
	
}
