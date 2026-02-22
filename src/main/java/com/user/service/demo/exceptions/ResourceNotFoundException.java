package com.user.service.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {


    //extra properties can be add...
    public ResourceNotFoundException(){
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
