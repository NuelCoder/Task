package com.project.projectapi.exceptions;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String message){
        super(message);
    }
}
