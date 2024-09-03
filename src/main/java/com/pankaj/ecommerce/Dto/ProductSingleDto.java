package com.pankaj.ecommerce.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductSingleDto {

    private Long productId;

    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private String categoryName;

    
}
