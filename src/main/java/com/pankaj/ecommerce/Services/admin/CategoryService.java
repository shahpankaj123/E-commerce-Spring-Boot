package com.pankaj.ecommerce.Services.admin;


import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Models.Category;

public interface CategoryService {
    Category CreateCategoryService(CategoryDto categoryDto);
}
