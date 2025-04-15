package com.project.projectapi.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.projectapi.entities.User;
import com.project.projectapi.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getUsers(
        @RequestParam(defaultValue = "") String email,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Page<User> userPage = userService.getUsers(email, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return ResponseEntity.ok(response);
    }
    

    @GetMapping("/{email}")
    public ResponseEntity<Map<String,Object>>retrieveUser(@PathVariable String email) {
        Optional<User>optionalUser = userService.getUserbyEmail(email);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Map<String,Object>response = new HashMap<>();

        response.put("Message", "User retrieved");
        response.put("User", optionalUser.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Map<String,Object>> deleteUsers(@PathVariable String email){
        Optional<User> optionalUser = userService.getUserbyEmail(email);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(email);

        Map<String,Object> response = new HashMap<>();

        response.put("Message", email + " has been deleted");

        return ResponseEntity.ok(response);

        
    }
    

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser (@PathVariable String email, @RequestBody User user) {
        User updatedUser = userService.updateUser(email, user);
        return ResponseEntity.ok(updatedUser);
    }
}
