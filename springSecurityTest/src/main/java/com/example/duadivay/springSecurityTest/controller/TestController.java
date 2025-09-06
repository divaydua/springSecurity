package com.example.duadivay.springSecurityTest.controller;

import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/test-users")
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }
}
