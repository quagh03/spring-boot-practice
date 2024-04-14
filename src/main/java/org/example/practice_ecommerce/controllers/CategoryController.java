package org.example.practice_ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.practice_ecommerce.dtos.requests.category.CategoryDTO;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.BaseResponse;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.ResponseFactory;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.SuccessResponse;
import org.example.practice_ecommerce.entities.Category;
import org.example.practice_ecommerce.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category")
@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Get all categories")
    @GetMapping("")
    public SuccessResponse<List<Category>> getAllCategories() {
        return ResponseFactory.createSuccessResponse(200, "Success", categoryService.getAllCategories());
    }

    @Operation(summary = "Get category by id", description = "Get category by id")
    @GetMapping("/{id}")
    public SuccessResponse<Category> getCategoryById(@Min(1) @PathVariable Integer id) {
        return ResponseFactory.createSuccessResponse(200, "Success", categoryService.getCategoryById(id));
    }

    @Operation(summary = "Delete category by id", description = "Delete category by id")
    @DeleteMapping("/{id}")
    public BaseResponse deleteCategoryById(@Min(1) @PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        return ResponseFactory.createResponse(true, HttpStatus.NO_CONTENT.value(), "Category deleted successfully");
    }

    @Operation(summary = "Create category", description = "Create category")
    @PostMapping("")
    public SuccessResponse<Category> createCategory(@Valid @RequestBody CategoryDTO category) {
        return ResponseFactory.createSuccessResponse(201, "Category created successfully", categoryService.createCategory(category));
    }

    @Operation(summary = "Active and deactive category", description = "Active and deactive category")
    @PatchMapping("/{id}/activation")
    public BaseResponse activeAndDeactiveCategory(@Min(1) @PathVariable Integer id) {
        Category category = categoryService.activeAndDeactiveCategory(id);

        String responseMessage = category.getIsActive() ? "Category activated successfully" : "Category deactivated successfully";

        return ResponseFactory.createResponse(true,201, responseMessage);
    }
}
