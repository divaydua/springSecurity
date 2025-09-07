package com.example.duadivay.springSecurityTest.controller;


import com.example.duadivay.springSecurityTest.dto.LoginDto;
import com.example.duadivay.springSecurityTest.dto.SignupDto;
import com.example.duadivay.springSecurityTest.dto.UserDto;
import com.example.duadivay.springSecurityTest.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Authcontroller {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(
                authService.signUp(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginDto loginDto,
            HttpServletRequest request,
            HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // it makes sure that this cookie cannot be accessed by any other it can only be fund with the help of your Http methods
// no other attacker can be access our website
// Prevents JavaScript access to the cookie
        response.addCookie(cookie); // Http only cookies can be passed from backend to frontend only


        return ResponseEntity.ok(token);
    }

}
