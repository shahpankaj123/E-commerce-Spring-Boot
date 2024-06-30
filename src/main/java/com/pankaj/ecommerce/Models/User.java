package com.pankaj.ecommerce.Models;

import com.pankaj.ecommerce.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;
    
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    
}
