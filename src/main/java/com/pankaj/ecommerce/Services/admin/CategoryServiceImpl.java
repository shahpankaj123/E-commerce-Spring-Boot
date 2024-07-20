package com.pankaj.ecommerce.Services.admin;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Exception.ResourceNotFoundException;
import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Repo.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    public Category CreateCategoryService(CategoryDto categoryDto){
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepo.save(category);
        return category;
    }
    public ResponseEntity<?> getResourceById(UUID id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok().body(category.get());
        } else {
            throw new ResourceNotFoundException("Product with ID Not Found");
        }
        
    }
    
}
