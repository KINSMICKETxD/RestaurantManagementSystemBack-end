package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service.login;

import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.LoginRequest;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService{


    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;


    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager,JWTUtil jwtUtil){

        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        String jwt = jwtUtil.generateToken(authentication);
        return jwt;
    }
}
