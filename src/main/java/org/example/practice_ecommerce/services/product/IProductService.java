package org.example.practice_ecommerce.services.product;

import org.example.practice_ecommerce.entities.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getProductById(Integer id);

    void deleteProductById(Integer id);
}
