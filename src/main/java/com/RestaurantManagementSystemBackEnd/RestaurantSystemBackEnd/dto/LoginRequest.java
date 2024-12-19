package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Setter
@ToString
public class LoginRequest {


    @NotBlank(message = "email cannot be blank.")
    @Email(message = "please enter a valid email.")
    private String username;


    @NotBlank(message = "password cannot be blank.")
    private String password;


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
