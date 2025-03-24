package com.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
    public  ResourceNotFoundException(){
        super("Resource not found on the server");
    }
}
