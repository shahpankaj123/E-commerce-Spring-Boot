package com.pankaj.ecommerce.Services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pankaj.ecommerce.Dto.SignupRequest;
import com.pankaj.ecommerce.Dto.UserDto;
import com.pankaj.ecommerce.Models.User;
import com.pankaj.ecommerce.Repo.UserRepo;
import com.pankaj.ecommerce.enums.UserRole;



@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepo userRepo;

    public UserDto createUser(SignupRequest signupRequest){
        User usr=new User();
        usr.setEmail(signupRequest.getEmail());
        usr.setName(signupRequest.getName());
        usr.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        usr.setRole(UserRole.CUSTOMER);
        User createUser=userRepo.save(usr);

        UserDto userDto=new UserDto();
        userDto.setId(createUser.getId());
        userDto.setEmail(createUser.getEmail());
        userDto.setName(createUser.getName());
        return userDto;
    }

    public Boolean hasUserWithEmail(String email){
        return userRepo.findFirstByEmail(email).isPresent();
    }
    
}
