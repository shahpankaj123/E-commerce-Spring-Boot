package com.pankaj.ecommerce.Services.auth;

import com.pankaj.ecommerce.Dto.SignupRequest;
import com.pankaj.ecommerce.Dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}