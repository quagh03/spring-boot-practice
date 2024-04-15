package org.example.practice_ecommerce.dtos.requests.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDTO {
    @NotBlank(message = "Category name is required")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Category description is required")
    @JsonProperty("is_active")
    private Boolean isActive;
}
