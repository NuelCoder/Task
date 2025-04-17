package com.project.projectapi.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class userDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    
    @NotBlank(message = "Please provide a firstname!")
    private String firstName;

    @NotBlank(message = "Please provide a lastname")
    private String lastName;

    @NotBlank(message = "Please provide a username")
    private String userName;

    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
        message = "Invalid email address"
    )
    @NotBlank(message = "Please provide an email")
    private String email;

    @NotBlank(message = "Please provide a phone number!")
    private String phoneNumber;

    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%.^&+=!]).{8,}$",
        message = "Password must be at least 8 characters long and contain uppercase, lowercase, digit, and special character"
    )

   
    @NotBlank(message = "Password is required")
    private String password;


    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }



}
