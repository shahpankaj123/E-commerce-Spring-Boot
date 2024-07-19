package com.pankaj.ecommerce.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Services.admin.CategoryService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final CategoryService categoryService;

    @Autowired
    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("category-create")
    public ResponseEntity<?> createcategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.CreateCategoryService(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}

