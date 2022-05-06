package dev.manool.TODOApp;

import dev.manool.TODOApp.repositories.SubtaskRepository;
import dev.manool.TODOApp.repositories.TaskRepository;
import dev.manool.TODOApp.tasks.Subtask;
import dev.manool.TODOApp.tasks.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        };
    }
}
