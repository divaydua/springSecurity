package com.example.duadivay.springSecurityTest.service;

import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.repo.UserRepo;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    // Constructor injection
    public CustomerDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));

        // Convert UserEntity to Spring Security's UserDetails
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) // Make sure password is encoded
                //   .roles(userEntity.getRole()) // If you have roles
                .build();
    }
}
