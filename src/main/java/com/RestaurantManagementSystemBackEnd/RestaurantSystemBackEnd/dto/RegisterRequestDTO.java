package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequestDTO {


    @NotBlank
    @Length(min = 1)
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "email is required.")
    private String email;


    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ'\\- ]+$",message = "First name contains invalid characters.")
    private String firstName;



    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ'\\- ]+$",message = "last name contains invalid characters.")
    private String lastName;



    @Length(min = 8,max = 64,message = "password must be at least 8 character and maximum 64 characters.")
    @Pattern(regexp  = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

}
