package dev.manool.TODOApp.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ProjectControllerAdvice {
    @ResponseBody()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    String constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return exception.getMessage();
    }

    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProjectNotFoundException.class)
    String projectNotFoundExceptionHandler(ProjectNotFoundException exception) {
        return exception.getMessage();
    }

}
