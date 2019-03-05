package com.stackroute.moviecruiser.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {

    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MovieAlreadyExistsException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "MovieAlreadyExistException{" +
                "message='" + message + '\'' +
                '}';
    }
}
