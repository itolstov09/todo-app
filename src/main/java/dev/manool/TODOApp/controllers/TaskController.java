package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.services.TaskService;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping()
    public Task saveTask(
//            @RequestBody
            Task newTask) {
        return taskService.saveTask(newTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteById(id));
    }



}
