package com.pankaj.ecommerce.Controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.ecommerce.Dto.AuthenticationRequest;
import com.pankaj.ecommerce.Models.User;
import com.pankaj.ecommerce.Repo.UserRepo;
import com.pankaj.ecommerce.Utilis.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepo userrepo;

    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX="Bearer ";

    public static final String HEADER_STRING="Authorization";

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
    HttpServletResponse response) throws JSONException, IOException{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password");
        }
        
        final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionaluser = userrepo.findFirstByEmail(userDetails.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails.getUsername());

        if (optionaluser.isPresent()) {
            User user = optionaluser.get();
        
            JSONObject jsonResponse = new JSONObject()
                    .put("UserId", user.getId())
                    .put("role", user.getRole());
        
            response.getWriter().write(jsonResponse.toString());
        
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
        }
        
    } 

    
}
