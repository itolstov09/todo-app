package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.repositories.TaskRepository;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/{id}")
    String findById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        //TODO убрать get
        return task.get().toString();
    }

}
