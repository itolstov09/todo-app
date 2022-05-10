package dev.manool.TODOApp.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class TaskControllerAdvice {

    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    String constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return exception.getMessage();
    }

    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    String taskNotFoundExceptionHandler(TaskNotFoundException exception) {
        return exception.getMessage();
    }


}

