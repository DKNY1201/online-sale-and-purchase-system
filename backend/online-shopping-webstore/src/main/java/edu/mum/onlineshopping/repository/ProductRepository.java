package edu.mum.onlineshopping.repository;

import java.io.Serializable;
import java.util.List;

import edu.mum.onlineshopping.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.onlineshopping.domain.Product;
import edu.mum.onlineshopping.domain.ProductType;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Serializable>{

	public List<Product> findByProductNameLikeOrDescriptionLikeAllIgnoreCase(String productName, String description); 
	public List<Product> findByProductType(ProductType productType); 
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	public List<Product> findAll();
	public List<Product> findByPerson(Person person);
}
