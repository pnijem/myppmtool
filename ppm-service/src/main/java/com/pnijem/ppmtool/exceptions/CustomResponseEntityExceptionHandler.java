package com.pnijem.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.Response;

/**
 * A convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling across all
 * @RequestMapping methods through @ExceptionHandler methods.
 * This base class provides an @ExceptionHandler method for handling internal Spring MVC exceptions.
 * This method returns a ResponseEntity for writing to the response with a message converter.
 * In order for an @ControllerAdvice subclass to be detected, ExceptionHandlerExceptionResolver must be configured.
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

