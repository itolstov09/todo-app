package dev.manool.TODOApp.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/tasks")
    public Task saveTask(
            @Valid
            @RequestBody
                    Task newTask) {
        return taskService.saveTask(newTask);
    }

    @PostMapping("/projects/{projectId}/tasks")
    public Task saveTask(
            @PathVariable Long projectId,
            @Valid
            @RequestBody
            Task newTask) {
        return taskService.saveTask(newTask, projectId);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @Valid
            @RequestBody
            Task updatedTask) {
        // Перестраховка. Вдруг при передаче данных забудут указать id
        updatedTask.setId(id);
        return taskService.saveTask(updatedTask);
    }

    // TODO Возможно по феншую здесь использовать DTO
    @PatchMapping("/tasks/{id}")
    public Task patchTask(
            @PathVariable Long id,
            @Valid
            @RequestBody
            Task taskInfo) {
        // Перестраховка. Вдруг при передаче данных забудут указать id
        taskInfo.setId(id);
        return taskService.patchTask(taskInfo);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteById(id));
    }



}
