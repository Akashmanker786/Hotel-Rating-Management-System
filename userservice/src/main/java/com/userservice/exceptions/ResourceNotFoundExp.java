package com.userservice.exceptions;


public class ResourceNotFoundExp extends RuntimeException{

    public ResourceNotFoundExp() {
        super("Resource Not found on server");
    }

    public ResourceNotFoundExp(String message){
        super(message);
    }


}
