package com.stackroute.moviecruiser.exception;

public class MovieNotFoundException extends Exception {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MovieNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MovieNotFountException{" +
                "message='" + message + '\'' +
                '}';
    }
}
