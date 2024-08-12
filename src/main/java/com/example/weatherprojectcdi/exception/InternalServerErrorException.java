package com.example.weatherprojectcdi.exception;

public class InternalServerErrorException extends Exception{
    public InternalServerErrorException(String mess) {
        super(mess);
    }

    public InternalServerErrorException() {
    }
}
