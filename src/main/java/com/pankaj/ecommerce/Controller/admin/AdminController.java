package com.pankaj.ecommerce.Controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pankaj.ecommerce.Dto.CategoryDto;
import com.pankaj.ecommerce.Dto.ProductDto;
import com.pankaj.ecommerce.Dto.ProductSingleDto;
import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Models.Product;
import com.pankaj.ecommerce.Services.admin.CategoryService;
import com.pankaj.ecommerce.Services.admin.ProductService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @Autowired
    private ProductService productService;

    @PostMapping("category-create")
    public ResponseEntity<?> createcategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.CreateCategoryService(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("category-get")
    public ResponseEntity<?> getcategory(@RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.getResourceById(id));
    }
    
    @GetMapping("product/list")
    public ResponseEntity<List<Product>> productlistView() {
        List<Product> products = productService.GetallProduct();
        return ResponseEntity.ok(products);
    } 
    
    @PostMapping("product-create")
    public ResponseEntity<Product> createProduct(
            @ModelAttribute ProductDto productDto, 
            @RequestParam("imageFile") MultipartFile imageFile) throws Exception {

        try {
            Product createdProduct;
            createdProduct = productService.create_product(productDto, imageFile);

            return ResponseEntity.ok(createdProduct);
            
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    
    @GetMapping("single-product")
    public ResponseEntity<?> getproductById(@RequestParam Long Id){
        ProductSingleDto productSingleDto= productService.SingleProduct(Id);
        return ResponseEntity.ok(productSingleDto);
    }

    @GetMapping("product-category-wise")
    public ResponseEntity<?> getproductBycategoryId(@RequestParam UUID Id){
        ProductSingleDto productSingleDto= productService.ProductByCategory(Id);
        return ResponseEntity.ok(productSingleDto);
    }

}

