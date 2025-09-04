package com.example.duadivay.springSecurityTest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authcontroller {

    @GetMapping("/")
    public String login() {
        return "You have succesfully logged in";
    }
    @GetMapping("/public")
    public String publicEndPoint(){
        return "This is a public end point";
    }
}
