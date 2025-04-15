package com.project.projectapi.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String,Object>>handleUserNotFound(UserNotFound ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Map<String,Object>>handleUserAlreadyExist(UserAlreadyExist ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordTooShort.class)
    public ResponseEntity<Map<String,Object>>handlePasswordLength(PasswordTooShort ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordFormat.class)
    public ResponseEntity<Map<String,Object>> handleInvalidPassword(InvalidPasswordFormat ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailFormat.class)
    public ResponseEntity<Map<String,Object>> handleInvalidEmail(InvalidEmailFormat ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NullEntry.class)
    public ResponseEntity<Map<String,Object>> handleNullEntry(NullEntry ex){
        Map<String,Object> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
