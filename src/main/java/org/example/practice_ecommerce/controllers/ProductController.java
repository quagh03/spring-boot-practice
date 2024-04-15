package org.example.practice_ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.BaseResponse;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.ResponseFactory;
import org.example.practice_ecommerce.dtos.responses.ApiResponse.SuccessResponse;
import org.example.practice_ecommerce.entities.Product;
import org.example.practice_ecommerce.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping()
    public SuccessResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseFactory.createSuccessResponse(HttpStatus.OK.value(), "Get all products successfully!", products);
    }

    @Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping("/{id}")
    public SuccessResponse<Product> getProductById(@Min(1) @PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return ResponseFactory.createSuccessResponse(HttpStatus.OK.value(), "Get product by id successfully!", product);
    }

    @Operation(summary = "Delete product by id", description = "Delete product by id")
    @DeleteMapping("/{id}")
    public BaseResponse deleteProductById(@Min(1) @PathVariable Integer id) {
        productService.deleteProductById(id);
        return ResponseFactory.createResponse(true, HttpStatus.NO_CONTENT.value(), "Delete product by id successfully!");
    }
}
