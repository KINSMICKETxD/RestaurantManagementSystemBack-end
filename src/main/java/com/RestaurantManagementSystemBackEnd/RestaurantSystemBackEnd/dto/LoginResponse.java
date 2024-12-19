package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {





    private String token;

    private String message;


    public LoginResponse(String token, String message){
        this.message = message;
        this.token = token  ;
    }

}
