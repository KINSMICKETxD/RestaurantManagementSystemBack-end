package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {



    @Value("${jwt.secret}")
    private String secretKey;


    @Value("${jwtExpiration}")
    private int jwtExpiration;




    public String generateToken(Authentication authentication){

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String tokenId = String.valueOf(new Random().nextInt(10000));

        return Jwts.builder()
                .setId(tokenId)
                .setSubject(userDetails.getUsername())
                .setIssuer("azizgh")
                .setAudience("XYZ_Ltd")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
                .compact();
    }


    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }


    public boolean validateJwtToken(String authToken){

        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        }catch (MalformedJwtException e){
            throw new MalformedJwtException("Invalid JWT Token : {} ");
        }catch (ExpiredJwtException e){
            throw new ExpiredJwtException(e.getHeader(),e.getClaims(),e.getMessage());
        }catch (UnsupportedJwtException e){
            throw new UnsupportedJwtException(e.getMessage());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public String getUsernameFromJwtToken(String authToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
    }




}
