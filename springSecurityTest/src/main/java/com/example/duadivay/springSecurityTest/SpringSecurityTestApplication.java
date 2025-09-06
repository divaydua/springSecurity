package com.example.duadivay.springSecurityTest;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.service.JWTService;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
public class SpringSecurityTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityTestApplication.class, args);
	}

		//System.out.println("Hello World");
}

