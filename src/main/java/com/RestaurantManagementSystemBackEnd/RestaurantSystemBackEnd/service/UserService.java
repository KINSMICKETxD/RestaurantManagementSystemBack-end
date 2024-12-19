package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service;

import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.RegisterRequestDTO;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.entity.User;

import java.util.Optional;

public interface UserService {


    Integer saveUser(RegisterRequestDTO registerRequestDTO);

    Optional<User> findByUsername(String email);

    Integer saveAdmin(RegisterRequestDTO registerRequestDTO);
}
