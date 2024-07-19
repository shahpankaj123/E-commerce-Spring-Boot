package com.pankaj.ecommerce.Services.admin;

import org.springframework.stereotype.Service;

import com.pankaj.ecommerce.Dto.CategoryDto;
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
    
}
