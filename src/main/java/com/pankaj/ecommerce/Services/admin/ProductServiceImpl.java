package com.pankaj.ecommerce.Services.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pankaj.ecommerce.Dto.ProductDto;
import com.pankaj.ecommerce.Exception.ResourceNotFoundException;
import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Models.Product;
import com.pankaj.ecommerce.Repo.CategoryRepo;
import com.pankaj.ecommerce.Repo.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;
     
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Product> GetallProduct(){
        return productRepo.findAll();
    }

    public Product create_product(ProductDto productDto,MultipartFile imageFile) throws IOException{

        Product product=new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        
        if (productDto.getCategory() != null && productDto.getCategory().getId() != null) {
            Category category = categoryRepo.findById(productDto.getCategory().getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            product.setCategory(category);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
        }

        productRepo.save(product);
        return product;
    }

    public Product SingleProduct(Long Id) {
        return productRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the given Id does not exist"));
    }
    
}
