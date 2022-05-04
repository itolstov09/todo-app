package dev.manool.TODOApp.services;

import dev.manool.TODOApp.exceptions.SubtaskNotFoundException;
import dev.manool.TODOApp.exceptions.TaskNotFoundException;
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

    //TODO попахивает. Надо переделать
    public Long deleteById(Long id) {
        // пытаемся найти запись с таким id
        subtaskRepository.findById(id).orElseThrow(() -> new SubtaskNotFoundException(id));
        // если нашли, то ошибки не происходит. Удаляем запись.
        subtaskRepository.deleteById(id);

        // так как удаление по id, запись с таким id может быть только одна. Потому возвращаем единицу
        return 1L;
    }
}
