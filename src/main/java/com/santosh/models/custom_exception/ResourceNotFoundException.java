package com.santosh.models.custom_exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
