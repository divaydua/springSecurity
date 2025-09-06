package com.example.duadivay.springSecurityTest.service;

import com.example.duadivay.springSecurityTest.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

//this is our service class

@Service
public class JWTService{

    //initialize a secreteKey
    // You can use any random key, but it must be a 256-bit key (32 bytes) for HMAC-SHA256

    private static final String jwtSecretKey = "this_is_a_very_long_secret_key_1234567890!";

    //generate a secret key method
    private SecretKey generateSecreteKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    //create token method
    public String createToken(UserEntity user) {
        return Jwts.builder()         //to build
                .subject(user.getId().toString())  // here we get the id which is in UserEntity class and Id is Long so we use toString()
                .claim("email",user.getEmail())   //in claim we have to add user details
            //    .claim("email", user.getEmail())
                //  .subject(user.getId().toString())
                .claim("roles", Set.of("ADMIN","USER"))  // if you define any role then you can use it like this
                .issuedAt(new Date())       // every token has a issueAt date
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))   // also has an expiration time, such as expiring 1 minute after creation
                .signWith(generateSecreteKey())     //signwith the key which is generated
                .compact();      // all of above need to compact

    }

    //to verify that token we use this method will give us the id that we are using to create this token

    public Long generateUserIdFromToken(String token) {
        Claims claims = Jwts.parser()   // to parse it
                .verifyWith(generateSecreteKey())// verify the key that we have
                .build()   // build the parser
                .parseSignedClaims(token)   //parse the token and get jws<claims>
                .getPayload();  //to get the payload

        return Long.valueOf(claims.getSubject());  //Assuming the user ID is stored as the subject in the token

    }

}