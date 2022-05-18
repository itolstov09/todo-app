package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.subtask.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.subtask.SubtaskRepository;
import dev.manool.TODOApp.subtask.Subtask;
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

    public Subtask findSubtaskById(Long id) {
        return subtaskRepository.findById(id)
                .orElseThrow(() -> new SubtaskNotFoundException(id));
    }

    public List<Subtask> findSubtasksByTaskId(Long taskId) {
        return subtaskRepository.findSubtasksByTaskId(taskId);
    }

    public Subtask save(Subtask newSubtask) {
        return subtaskRepository.save(newSubtask);
    }

    public void deleteById(Long id) {
        subtaskRepository.findById(id).orElseThrow(() -> new SubtaskNotFoundException(id));
        subtaskRepository.deleteById(id);
    }
}
