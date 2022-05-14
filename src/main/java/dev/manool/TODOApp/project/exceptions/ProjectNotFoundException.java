package dev.manool.TODOApp.project.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id %s not found!", id));
    }
}
