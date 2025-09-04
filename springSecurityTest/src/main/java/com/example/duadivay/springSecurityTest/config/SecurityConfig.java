package com.example.duadivay.springSecurityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        var user = org.springframework.security.core.userdetails.User
                .withUsername("divay")
                .password(passwordEncoder.encode("pass"))  // bcrypt encoded
                .roles("USER")
                .build();

        var admin = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(passwordEncoder.encode("pass@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSecurity logout = httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/public/**").permitAll()   // give any of your 'get' request endpoint
                                .anyRequest().authenticated())  // All other requests require authentication

                .csrf(csrfConfig ->  // Disable CSRF for simplicity, not recommended for production
                        csrfConfig
                                .disable())
                .sessionManagement(sessionConfig ->   // Disable JSESSIONID for simplicity, not recommended for production
                        sessionConfig
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .formLogin(Customizer.withDefaults())  // Enable form login here I use default login

                .logout(Customizer.withDefaults());// Enable logout

        return httpSecurity.build();   //when you add build this throws an exception
    }
}