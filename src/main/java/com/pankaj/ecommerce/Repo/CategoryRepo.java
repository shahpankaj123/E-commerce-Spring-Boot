package com.pankaj.ecommerce.Repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankaj.ecommerce.Models.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,UUID>{
    
}
