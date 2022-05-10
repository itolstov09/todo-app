package dev.manool.TODOApp.task;

import dev.manool.TODOApp.task.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

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

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    //TODO попахивает. Надо переделать
    public Long deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            logger.info(String.format("Cannot delete task with id %s: task not found!", id));
            throw new TaskNotFoundException(id);
        }
        // если нашли, то ошибки не происходит. Удаляем запись.
        taskRepository.deleteById(id);
        logger.info(String.format("Task with id %s was deleted", id));

        // так как удаление по id, запись с таким id может быть только одна. Потому возвращаем единицу
        return 1L;
    }

    public Task patchTask(Task taskInfo) {
        // запрашиваем задачу с тем же id
        // проходим по каждому полю taskInfo,
        // если есть значение то передаем в объект задачи
        long taskInfoId = taskInfo.getId();
        Task task = taskRepository.findById(taskInfoId)
                .orElseThrow(() -> new TaskNotFoundException(taskInfoId));

        logger.debug(String.format("Update task from entity: %s", taskInfo));
        boolean isNeedUpdate = false;

        String taskInfoText = taskInfo.getText();
        if (taskInfoText != null && !taskInfoText.isEmpty()) {
            task.setText(taskInfoText);
            isNeedUpdate = true;
        }
        Task.Status taskInfoStatus = taskInfo.getStatus();
        if (taskInfoStatus != null) {
            task.setStatus(taskInfoStatus);
            isNeedUpdate = true;
        }
        Task.Priority taskInfoPriority = taskInfo.getPriority();
        if (taskInfoPriority != null) {
            task.setPriority(taskInfoPriority);
            isNeedUpdate = true;
        }
        LocalDate taskInfoDeadline = taskInfo.getDeadline();
        if (taskInfoDeadline != null) {
            task.setDeadline(taskInfoDeadline);
            isNeedUpdate = true;
        }
//        // TODO Скорее всего здесь нужен patch для подзадач
        // Бесполезная затея. Нужно делать patch запрос в subtaskservice
//        Set<Subtask> taskInfoSubTasks = taskInfo.getSubTasks();
//        if (taskInfoSubTasks != null) {
//            task.setSubTasks(taskInfoSubTasks);
//            isNeedUpdate = true;
//        }
        if (isNeedUpdate) {
            logger.debug(String.format("Save updated task entity: %s", task));
            taskRepository.save(task);
        }

        return task;
    }
}
