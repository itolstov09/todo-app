package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.subtask.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.task.Task;
import dev.manool.TODOApp.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {
    private final SubtaskRepository subtaskRepository;
    private final TaskService taskService;

    @Autowired
    public SubtaskService(SubtaskRepository subtaskRepository, TaskService taskService) {
        this.subtaskRepository = subtaskRepository;
        this.taskService = taskService;
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

    public Subtask save(Subtask newSubtask, Long taskId) {
        newSubtask.setTask(taskService.findTaskById(taskId));
        return subtaskRepository.save(newSubtask);
    }

    public Subtask save(Subtask subtaskInfo, Long taskId, Long subtaskId) {
        Task task = taskService.findTaskById(taskId);
        subtaskInfo.setTask(task);
        // Перестраховка. вдруг забыли указать id в теле запроса
        subtaskInfo.setId(subtaskId);
        return subtaskRepository.save(subtaskInfo);
    }
    public void deleteById(Long id) {
        subtaskRepository.findById(id).orElseThrow(() -> new SubtaskNotFoundException(id));
        subtaskRepository.deleteById(id);
    }

}
