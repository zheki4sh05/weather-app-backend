package com.example.weatherprojectcdi.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
    }
}
