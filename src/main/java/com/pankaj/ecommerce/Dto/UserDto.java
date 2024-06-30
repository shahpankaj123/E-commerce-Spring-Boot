package com.pankaj.ecommerce.Dto;

import com.pankaj.ecommerce.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String Email;
    private String name;
    private UserRole role;
    
}
