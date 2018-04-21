package mum.edu.pm.service;

import mum.edu.pm.entity.Product;
import mum.edu.pm.repository.IProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService  extends JpaRepository<Product, Long>, IProductRepository {
}
