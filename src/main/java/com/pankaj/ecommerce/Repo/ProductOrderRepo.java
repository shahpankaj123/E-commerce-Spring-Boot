package com.pankaj.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankaj.ecommerce.Models.ProductOrder;
import java.util.UUID;

public interface ProductOrderRepo extends JpaRepository<ProductOrder,UUID>{
    
}
