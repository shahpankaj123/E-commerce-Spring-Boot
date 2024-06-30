package com.pankaj.ecommerce.Controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.ecommerce.Dto.AuthenticationRequest;
import com.pankaj.ecommerce.Dto.SignupRequest;
import com.pankaj.ecommerce.Dto.UserDto;
import com.pankaj.ecommerce.Models.User;
import com.pankaj.ecommerce.Repo.UserRepo;
import com.pankaj.ecommerce.Services.auth.AuthService;
import com.pankaj.ecommerce.Utilis.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final UserRepo userrepo;

    @Autowired
    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX="Bearer ";

    public static final String HEADER_STRING="Authorization";

    @Autowired
    private final AuthService authService;


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
            response.getWriter().write(new JSONObject()
            .put("UserId", optionaluser.get().getId())
            .put("role", optionaluser.get().getRole())
            .toString()
            );
            response.addHeader("Access-Control-Expose-Headers","Authorization");
            response.addHeader("Access-Control-Allow-Headers","Authorization,X-PINGOTHER,Origin," + "X-Requested-With,Conent-Type,Accept,X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
        }
        
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {

        if(authService.hasUserWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("User already Exits",HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto =authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    
}
