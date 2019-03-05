package com.stackroute.moviecruiser.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
