package com.pankaj.ecommerce.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pankaj.ecommerce.Models.User;
import com.pankaj.ecommerce.Repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionaluser =userrepo.findFirstByEmail(username);
        if (optionaluser.isEmpty()) throw new UsernameNotFoundException("Username Not Found",null);
        return new org.springframework.security.core.userdetails.User(optionaluser.get().getEmail(),optionaluser.get().getPassword(),new ArrayList<>());
    }
    
}
