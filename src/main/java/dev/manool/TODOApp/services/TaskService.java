package dev.manool.TODOApp.services;

import dev.manool.TODOApp.exceptions.TaskNotFoundException;
import dev.manool.TODOApp.repositories.TaskRepository;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;


    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    //TODO попахивает. Надо переделать
    public Long deleteById(Long id) {
        // пытаемся найти запись с таким id
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        // если нашли, то ошибки не происходит. Удаляем запись.
        taskRepository.deleteById(id);

        // так как удаление по id, запись с таким id может быть только одна. Потому возвращаем единицу
        return 1L;
    }
}
