package com.pankaj.ecommerce.Services.admin;


import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Models.Category;

public interface CategoryService {
    Category CreateCategoryService(CategoryDto categoryDto);
    ResponseEntity<?> getResourceById(UUID id);
}
