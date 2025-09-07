package com.example.duadivay.springSecurityTest.dto;

import lombok.Data;

@Data
public class SignupDto {

    //you can add validation here
    private String email;
    private String password;
    private String name;

}
