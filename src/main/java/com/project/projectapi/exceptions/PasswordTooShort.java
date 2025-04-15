package com.project.projectapi.exceptions;

public class PasswordTooShort extends RuntimeException{
    public PasswordTooShort(String message){
        super(message);
    }
}
