package com.pankaj.ecommerce.Dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String Email;

    private String password;

    private String name;
    
}
