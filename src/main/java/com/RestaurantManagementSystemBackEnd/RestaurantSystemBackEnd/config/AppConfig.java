package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }




    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

}
