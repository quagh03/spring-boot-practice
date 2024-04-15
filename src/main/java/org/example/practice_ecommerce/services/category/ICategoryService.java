package org.example.practice_ecommerce.services.category;

import org.example.practice_ecommerce.dtos.requests.category.CategoryDTO;
import org.example.practice_ecommerce.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    void deleteCategoryById(Integer id);
    Category createCategory(CategoryDTO category);

    Category activeAndDeactiveCategory(Integer id);

    Category updateCategory(Integer id, CategoryDTO category);

    List<Category> getAllCategoriesByStatus(Boolean active);
}
