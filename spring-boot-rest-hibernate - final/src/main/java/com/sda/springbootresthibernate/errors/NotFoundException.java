package com.sda.springbootresthibernate.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

    public NotFoundException(final String message){
        super(message);
    }
}
