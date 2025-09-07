package com.example.duadivay.springSecurityTest.exceptions;

import com.example.duadivay.springSecurityTest.entities.ApiError;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {


//AuthorizationException

    // Handle a specific exception (e.g., AccessDeniedException)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);

    }


//AuthenticationException

        // Handle a specific exception (e.g., AuthenticationException)
        //
      @ExceptionHandler(value = AuthenticationException.class)
        public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e){
            //It returns an ApiError with a message and a 401 Unauthorized status.
            ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
        }


        //JWTException

// Handle a specific exception (e.g., JwtException)
        @ExceptionHandler(JwtException.class)
        public ResponseEntity<ApiError> handleJwtException(JwtException e){
            //It returns an ApiError with a message and a 401 Unauthorized status.
            ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
        }

    }
