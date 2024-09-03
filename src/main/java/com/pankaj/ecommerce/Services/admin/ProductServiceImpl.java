package com.pankaj.ecommerce.Services.admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pankaj.ecommerce.Dto.ProductDto;
import com.pankaj.ecommerce.Dto.ProductSingleDto;
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
    public ProductSingleDto SingleProduct(Long Id) {
        Product product=productRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the given Id does not exist"));

        ProductSingleDto productSingleDto  = new ProductSingleDto();

        productSingleDto.setName(product.getName());
        productSingleDto.setPrice(product.getPrice());
        productSingleDto.setDescription(product.getDescription());
        productSingleDto.setCategoryName(product.getCategory().getName());
        productSingleDto.setProductId(product.getProductId());
        productSingleDto.setQuantity(product.getQuantity());

        return productSingleDto;
    }
    
    public ProductSingleDto ProductByCategory(UUID Id) {
        Category category = categoryRepo.findById(Id)
            .orElseThrow(() -> new ResourceNotFoundException("Category with the given Id does not exist"));

        Product product = productRepo.findByCategory(category)
            .orElseThrow(() -> new ResourceNotFoundException("Product with the given Category Id does not exist"));

        ProductSingleDto productSingleDto  = new ProductSingleDto();

        productSingleDto.setName(product.getName());
        productSingleDto.setPrice(product.getPrice());
        productSingleDto.setDescription(product.getDescription());
        productSingleDto.setCategoryName(product.getCategory().getName());
        productSingleDto.setProductId(product.getProductId());
        productSingleDto.setQuantity(product.getQuantity());

        return productSingleDto;
    }

    
}
