package dev.manool.TODOApp.subtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SubtaskControllerAdvice {

    @ResponseBody()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SubtaskNotFoundException.class)
    String subtaskNotFoundExceptionHandler(SubtaskNotFoundException exception) {
        return exception.getMessage();
    }


}

