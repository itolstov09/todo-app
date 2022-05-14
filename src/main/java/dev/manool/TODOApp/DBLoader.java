package dev.manool.TODOApp;

import dev.manool.TODOApp.project.Project;
import dev.manool.TODOApp.project.ProjectRepository;
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
    public CommandLineRunner fillDB(
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            SubtaskRepository subTaskRepository) {
        return args -> {
            Project project = new Project("first project");
            projectRepository.save(project);

            Task task = new Task(
                    "task_text",
                    Task.Status.NOT_STARTED,
                    Task.Priority.NORMAL,
                    project);

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
            Iterable<Project> projects = projectRepository.findAll();
            for (Project projectEl : projects) {
                logger.debug(projectEl.toString());
            }

        };
    }
}
