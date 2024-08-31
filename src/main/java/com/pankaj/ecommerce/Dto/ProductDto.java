package com.pankaj.ecommerce.Dto;

import java.math.BigDecimal;

import com.pankaj.ecommerce.Models.Category;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private Category category;
    
}
