package com.santosh.models.custom_exception;

public class UserAlreadyPresentException extends Exception{

    public UserAlreadyPresentException(String msg){
        super(msg);
    }
}

