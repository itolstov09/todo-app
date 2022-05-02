package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.services.SubtaskService;
import dev.manool.TODOApp.services.TaskService;
import dev.manool.TODOApp.tasks.Subtask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubtaskController {
    private final SubtaskService subtaskService;
    private final TaskService taskService;


    public SubtaskController(SubtaskService subtaskService, TaskService taskService) {
        this.subtaskService = subtaskService;
        this.taskService = taskService;
    }

    @GetMapping("tasks/{taskId}/subtasks")
    public List<Subtask> findSubtasksByTaskId(@PathVariable Long taskId) {
        return subtaskService.findSubtasksByTaskId(taskId);
    }

    @PostMapping("tasks/{taskId}/subtasks")
    public Subtask saveSubtask(@PathVariable Long taskId, Subtask newSubtask) {
        // FIXME Попахивает лютым костылём. Стоит починить или сделать по уму.
        newSubtask.setTask(taskService.findTaskById(taskId));
        return subtaskService.save(newSubtask);
    }

    @DeleteMapping("subtasks/{id}")
    public ResponseEntity<Long> deleteSubtaskById(@PathVariable Long id) {
        return ResponseEntity.ok(subtaskService.deleteById(id));
    }
}
