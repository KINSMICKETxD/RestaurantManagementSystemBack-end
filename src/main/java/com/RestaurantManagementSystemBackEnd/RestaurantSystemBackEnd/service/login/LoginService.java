package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service.login;

import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.LoginRequest;

public interface LoginService {

    String loginUser(LoginRequest loginRequest);
}
