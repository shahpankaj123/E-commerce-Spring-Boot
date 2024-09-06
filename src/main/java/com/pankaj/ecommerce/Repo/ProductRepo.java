package com.pankaj.ecommerce.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.pankaj.ecommerce.Models.Category;
import com.pankaj.ecommerce.Models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{
   List<Product> findByCategory(Category category);
   List<Product> findByName(String key);

}