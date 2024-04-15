package org.example.practice_ecommerce.repositories;

import org.example.practice_ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
