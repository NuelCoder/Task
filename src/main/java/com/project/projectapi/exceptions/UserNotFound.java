package com.project.projectapi.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}
