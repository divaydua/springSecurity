package com.example.duadivay.springSecurityTest;

import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JWTApplicationTests {

    @Autowired
    private JWTService jwtService;

    @Test
    void testJWTCreationAndValidation() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("test");

        String token = jwtService.createToken(user);
        System.out.println("Create Token: " + token);

        Long id = jwtService.generateUserIdFromToken(token);
        System.out.println("Generate Id from Token: " + id);

        assertEquals(user.getId(), id);
    }
}