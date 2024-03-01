package com.hotel.exceptions;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String message) {
        super(message);
    }
    public ResourseNotFoundException() {
        super("Resource not found");
    }
}
