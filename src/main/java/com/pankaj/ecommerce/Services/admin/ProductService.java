package com.pankaj.ecommerce.Services.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pankaj.ecommerce.Dto.ProductDto;
import com.pankaj.ecommerce.Models.Product;

public interface ProductService {
    public List<Product> GetallProduct(); 
    public Product create_product(ProductDto productDto,MultipartFile imageFile) throws Exception; 
    public Product SingleProduct(Long Id);
}
