package com.pankaj.ecommerce.Dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String Username;
    
    private String password;
    
}
