package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.services.SubtaskService;
import dev.manool.TODOApp.services.TaskService;
import dev.manool.TODOApp.tasks.Subtask;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

}
