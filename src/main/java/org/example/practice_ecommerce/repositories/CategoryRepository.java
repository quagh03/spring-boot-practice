package org.example.practice_ecommerce.repositories;

import org.example.practice_ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    List<Category> findAllByIsActiveTrue();
    List<Category> findAllByIsActiveFalse();
}
