package com.spring_app.sandbox.controller;

import java.util.UUID;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring_app.sandbox.domain.dto.ErrorDto;
import com.spring_app.sandbox.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
        // Extract validation error messages from the exception and return a structured error response.
        // get field errors to get list of errors, 
        String errorMessage = ex.getBindingResult().getFieldErrors()
            .stream().findFirst() // stream because only want first one and findFirst to get the first error message
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElse("Validation Failed");
       
        ErrorDto errorDto = new ErrorDto(errorMessage);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }   

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        UUID taskNotFoundId = ex.getId();
        String errorMessage = String.format("Task with id '%s' not found.", taskNotFoundId);
        ErrorDto errorDto = new ErrorDto(errorMessage);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
