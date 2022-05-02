package dev.manool.TODOApp.controllers;

import dev.manool.TODOApp.services.SubtaskService;
import dev.manool.TODOApp.tasks.Subtask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubtaskController {
    private final SubtaskService subtaskService;


    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("tasks/{taskId}/subtasks")
    List<Subtask> findSubtasksByTaskId(@PathVariable Long taskId) {
        return subtaskService.findSubtasksByTaskId(taskId);
    }



}
