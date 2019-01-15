package com.pnijem.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //whenever this exception is thrown, the user will get HTTP 404 (Bad Request)
public class ProjectIdException extends RuntimeException{

    public ProjectIdException(String message){
        super(message);
    }


}
