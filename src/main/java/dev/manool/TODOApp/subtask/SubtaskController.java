package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.task.TaskService;
import dev.manool.TODOApp.task.Task;
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

    @GetMapping("subtasks")
    public List<Subtask> findAllSubtasks() {
        return subtaskService.findAllSubtasks();
    }

    @GetMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public Subtask findSubtaskById(@PathVariable Long subtaskId) {
        return subtaskService.findSubtaskById(subtaskId);
    }

    @PostMapping("tasks/{taskId}/subtasks")
    public Subtask saveSubtask(
            @PathVariable Long taskId,
            @RequestBody
            Subtask newSubtask) {
        // FIXME Действие ОК, реализация не очень.
        //  Попахивает лютым костылём.
        //  Стоит починить или сделать по уму. Например через Optional
        newSubtask.setTask(taskService.findTaskById(taskId));
        return subtaskService.save(newSubtask);
    }

    @PutMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public Subtask updateSubtask(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @RequestBody Subtask subtaskInfo) {
        Task task = taskService.findTaskById(taskId);
        subtaskInfo.setTask(task);
        // Перестраховка. вдруг забыли указать id в теле запроса
        subtaskInfo.setId(subtaskId);

        return subtaskService.save(subtaskInfo);
    }

    @PatchMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public Subtask patchSubtask(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @RequestBody Subtask subtaskInfo) {
        Task task = taskService.findTaskById(taskId);
        subtaskInfo.setTask(task);
       // Перестраховка. вдруг забыли указать id в теле запроса
        subtaskInfo.setId(subtaskId);
        return subtaskService.save(subtaskInfo);
    }


    @DeleteMapping("tasks/{taskId}/subtasks/{subtaskId}")
    public ResponseEntity<Long> deleteSubtaskById(@PathVariable Long subtaskId) {
        return ResponseEntity.ok(subtaskService.deleteById(subtaskId));
    }
}
