package com.example.duadivay.springSecurityTest.service;

import com.example.duadivay.springSecurityTest.dto.LoginDto;
import com.example.duadivay.springSecurityTest.dto.SignupDto;
import com.example.duadivay.springSecurityTest.dto.UserDto;
import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserDto signUp(SignupDto signupDto) {

        // Check if a user already exists with the provided email

        Optional<UserEntity> user = userRepo
                .findByEmail(signupDto.getEmail());

        if(user.isPresent()) throw new BadCredentialsException("Cannot signup, User already exists with email "+signupDto.getEmail());

        // Map SignupDto to UserEntity and encode the password

        UserEntity mappedUser = modelMapper.map(signupDto,UserEntity.class);
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));

        // Save the user entity to the database
        UserEntity savedUser = userRepo.save(mappedUser);

// Map the saved UserEntity to UserDto and return it
        return modelMapper.map(savedUser,UserDto.class);
    }

   public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        // Get the username from the authenticated principal
        String username = authentication.getName();
        
        // Find the user entity by email
        UserEntity user = userRepo.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

       return jwtService.createToken(user);
   }
}
