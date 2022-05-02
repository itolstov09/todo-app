package dev.manool.TODOApp.services;

import dev.manool.TODOApp.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.repositories.SubtaskRepository;
import dev.manool.TODOApp.tasks.Subtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {
    private final SubtaskRepository subtaskRepository;

    @Autowired
    public SubtaskService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    public List<Subtask> findAllSubtasks() {
        return subtaskRepository.findAll();
    }

    public Subtask findTaskById(Long id) {
        return subtaskRepository.findById(id)
                .orElseThrow(() -> new SubtaskNotFoundException(id));
    }

    public List<Subtask> findSubtasksByTaskId(Long taskId) {
        return subtaskRepository.findSubtasksByTaskId(taskId);
    }
}
