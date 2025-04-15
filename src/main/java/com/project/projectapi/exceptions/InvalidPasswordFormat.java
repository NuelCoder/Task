package com.project.projectapi.exceptions;

public class InvalidPasswordFormat extends RuntimeException{
    public InvalidPasswordFormat(String message){
        super(message);
    }
}
