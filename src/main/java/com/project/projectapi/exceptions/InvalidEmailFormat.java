package com.project.projectapi.exceptions;

public class InvalidEmailFormat extends RuntimeException{
    public InvalidEmailFormat(String message){
        super(message);
    }
}
