package dev.manool.TODOApp.task.exceptions;

public class TaskNotFoundException extends  RuntimeException{

    public TaskNotFoundException(Long id) {
        super(String.format("Task with id %s not found!", id));
    }
}
