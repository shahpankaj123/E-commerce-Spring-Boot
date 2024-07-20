package com.pankaj.ecommerce.Dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDto {

    public UUID id;
    public String name;
    public String description;
        
}
