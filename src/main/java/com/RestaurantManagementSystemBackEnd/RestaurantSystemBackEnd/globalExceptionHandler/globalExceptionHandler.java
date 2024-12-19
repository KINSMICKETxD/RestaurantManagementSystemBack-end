package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.globalExceptionHandler;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {


    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception){


        return ResponseEntity.badRequest().body(exception.getMessage());
    }


   @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){


       String errorMessage = exception.getBindingResult()
               .getAllErrors()
               .stream()
               .map(error -> error.getDefaultMessage())
               .findFirst()
               .orElse("Validation error");

        return ResponseEntity.badRequest().body(errorMessage);
   }

}
