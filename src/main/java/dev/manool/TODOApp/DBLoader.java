package dev.manool.TODOApp;

import dev.manool.TODOApp.subtask.SubtaskRepository;
import dev.manool.TODOApp.task.TaskRepository;
import dev.manool.TODOApp.subtask.Subtask;
import dev.manool.TODOApp.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;import java.util.Arrays;

@Configuration
public class DBLoader {

    private final Logger logger = LoggerFactory.getLogger(DBLoader.class);

    // TODO Переделать в тесты
    @Bean
    public CommandLineRunner fillDB(TaskRepository taskRepository,
                                               SubtaskRepository subTaskRepository) {
        return args -> {
            Task task = new Task(
                    "task_text",
                    Task.Status.NOT_STARTED,
                    Task.Priority.NORMAL);

            taskRepository.save(task);

            subTaskRepository.save(
                    new Subtask("sub task text", false, task)
            );
            subTaskRepository.save(
                    new Subtask("second subtask", true, task)
            );
            subTaskRepository.save(
                    new Subtask("third subtask", true, task)
            );
            Iterable<Task> tasks = taskRepository.findAll();
            for (Task taskEl : tasks) {
                logger.debug(taskEl.toString());
            }

            Field[] fields = Task.class.getDeclaredFields();
            for (Field field: fields) {
                logger.debug(String.format(
                        "Task table field: %s",
                        Arrays.toString(field.getAnnotations()))
                );
            }
        };
    }
}
