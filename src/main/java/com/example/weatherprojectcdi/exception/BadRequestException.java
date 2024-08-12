package com.example.weatherprojectcdi.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
    }
}
