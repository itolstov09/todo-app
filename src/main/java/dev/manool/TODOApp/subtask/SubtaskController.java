package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.task.TaskService;
import dev.manool.TODOApp.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubtaskController {
    private final SubtaskService subtaskService;


    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("tasks/{taskId}/subtasks")
    public List<Subtask> findSubtasksByTaskId(@PathVariable Long taskId) {
        return subtaskService.findSubtasksByTaskId(taskId);
    }

    @GetMapping("subtasks")
    public List<Subtask> findAllSubtasks() {
        return subtaskService.findAllSubtasks();
    }

    @GetMapping("/subtasks/{subtaskId}")
    public Subtask findSubtaskById(@PathVariable Long subtaskId) {
        return subtaskService.findSubtaskById(subtaskId);
    }


    @PostMapping("/tasks/{taskId}/subtasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Subtask saveSubtask(
            @PathVariable Long taskId,
            @Valid
            @RequestBody
            Subtask newSubtask) {
        return subtaskService.save(newSubtask, taskId);
    }

    @PutMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public Subtask updateSubtask(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @Valid
            @RequestBody Subtask subtaskInfo) {
        return subtaskService.save(subtaskInfo, taskId, subtaskId);
    }

    @PatchMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public Subtask patchSubtask(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @Valid
            @RequestBody Subtask subtaskInfo) {
        return subtaskService.save(subtaskInfo, taskId, subtaskId);
    }


    @DeleteMapping("/subtasks/{subtaskId}")
    public ResponseEntity<?> deleteSubtaskById(@PathVariable Long subtaskId) {
        subtaskService.deleteById(subtaskId);
        return ResponseEntity.noContent().build();
    }
}
