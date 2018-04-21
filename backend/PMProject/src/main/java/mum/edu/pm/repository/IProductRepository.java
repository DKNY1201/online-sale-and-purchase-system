package mum.edu.pm.repository;

import mum.edu.pm.entity.Product;

import java.util.List;

public interface IProductRepository {
    public default List<Product> getProductByName(String item_name) {
        return null;
    }
}
