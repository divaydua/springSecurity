package com.example.duadivay.springSecurityTest.service;

import com.example.duadivay.springSecurityTest.entities.UserEntity;
import com.example.duadivay.springSecurityTest.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
   private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByEmail(username)

                // .orElse(null);
                //or you can throw an exception here
                .orElseThrow(()->new UsernameNotFoundException("USER WITH EMAIL "+username+" NOT FOUND"));
    }

    public Optional<UserEntity> getUserById(Long userId) {
        return userRepo.findById(userId);

    }
}
