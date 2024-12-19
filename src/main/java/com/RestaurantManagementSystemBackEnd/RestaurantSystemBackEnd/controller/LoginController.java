package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.controller;


import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.LoginRequest;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.LoginResponse;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service.UserService;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service.login.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {


    private UserService userService;


    private AuthenticationManager authenticationManager;

    private LoginService loginService;


    @Autowired
    public LoginController(UserService userService,
                           AuthenticationManager authenticationManager,
                           LoginService loginService){
        this.userService = userService;

        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest){

        String token = this.loginService.loginUser(loginRequest);

        LoginResponse loginResponse = new LoginResponse(token,"successful login.");

        return ResponseEntity.ok(loginResponse);
    }
}
