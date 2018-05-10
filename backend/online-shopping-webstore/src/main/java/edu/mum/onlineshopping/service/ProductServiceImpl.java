package edu.mum.onlineshopping.service;

import edu.mum.onlineshopping.domain.Person;
import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;
import edu.mum.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
		
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product getProduct(int productId) {
		return  productRepository.findOne(productId);
	}

	public List<Product> getAllProduct() {

		return  productRepository.findAll() ;
	}

	public List<Product> getProductByPerson(Person person) {

		return  productRepository.findByPerson(person) ;
	}
	
	public List<Product> findByTextSearch(String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%"+criteria+"%";
		}
		return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
	}

	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return  productRepository.findByPriceBetween(minPrice, maxPrice);
	}
	
	public List<Product> findByProductType(ProductType productType) {
		 return productRepository.findByProductType(productType);
	}

	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}
	
}
