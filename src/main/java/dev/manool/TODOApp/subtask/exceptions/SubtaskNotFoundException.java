package dev.manool.TODOApp.subtask.exceptions;

public class SubtaskNotFoundException extends RuntimeException{
    public SubtaskNotFoundException(Long id) {
        super(String.format("Subtask with id %s not found!", id));
    }
}
