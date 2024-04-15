package org.example.practice_ecommerce.services.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.practice_ecommerce.entities.Product;
import org.example.practice_ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
