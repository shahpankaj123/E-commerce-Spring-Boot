package com.pankaj.ecommerce.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankaj.ecommerce.Models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

}