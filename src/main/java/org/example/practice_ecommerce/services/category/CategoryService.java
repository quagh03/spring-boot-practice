package org.example.practice_ecommerce.services.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.practice_ecommerce.dtos.requests.category.CategoryDTO;
import org.example.practice_ecommerce.entities.Category;
import org.example.practice_ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        Category foundCategory = getCategoryById(id);
        categoryRepository.delete(foundCategory);
    }

    @Override
    @Transactional
    public Category createCategory(CategoryDTO category) {
        Category newCategory = new Category();

        if(categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category with name " + category.getName() + " already exists");
        }

        mapper.map(category, newCategory);

        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category activeAndDeactiveCategory(Integer id) {
        Category foundCategory = getCategoryById(id);
        foundCategory.setIsActive(!foundCategory.getIsActive());
        return categoryRepository.save(foundCategory);
    }
}
