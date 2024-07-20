package com.pankaj.ecommerce.Controller.admin;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Services.admin.CategoryService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("category-create")
    public ResponseEntity<?> createcategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.CreateCategoryService(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("category-get")
    public ResponseEntity<?> getcategory(@RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.getResourceById(id));
    }

}

