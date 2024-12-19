package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.controller;


import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.RegisterRequestDTO;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegisterController {


    private UserService userService;


    private AuthenticationManager authenticationManager;


    @Autowired
    public RegisterController(UserService userService,AuthenticationManager authenticationManager){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){


        Integer userId = this.userService.saveUser(registerRequestDTO);

        if(userId > 0){
            return ResponseEntity.ok("User registered successfully.");
        }else{
            return ResponseEntity.internalServerError().body("Error occurs while trying to save the usr.");
        }

    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){


        Integer adminId = this.userService.saveAdmin(registerRequestDTO);

        if(adminId > 0){
            return ResponseEntity.ok("Admin registered successfully.");
        }else{
            return ResponseEntity.internalServerError().body("Error occurs while trying to save the Admin.");
        }
    }
}
