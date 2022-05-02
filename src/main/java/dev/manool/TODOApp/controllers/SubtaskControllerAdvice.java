package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class SubtaskControllerAdvice {

    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    String constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return exception.getMessage();
    }

    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    String subtaskNotFoundExceptionHandler(SubtaskNotFoundException exception) {
        return exception.getMessage();
    }


}

