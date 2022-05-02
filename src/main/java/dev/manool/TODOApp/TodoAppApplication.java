package dev.manool.TODOApp;

import dev.manool.TODOApp.repositories.SubTaskRepository;
import dev.manool.TODOApp.repositories.TaskRepository;
import dev.manool.TODOApp.tasks.SubTask;
import dev.manool.TODOApp.tasks.Task;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class TodoAppApplication {

	private final Logger logger = LoggerFactory.getLogger(TodoAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	// TODO Переделать в тесты
	@Bean
	public CommandLineRunner commandLineRunner(TaskRepository taskRepository,
											   SubTaskRepository subTaskRepository) {
		return args -> {
			Task task = new Task(
					"task_text",
					Task.Status.NOT_STARTED,
					Task.Priority.NORMAL,
					LocalDate.now());

			taskRepository.save(task);

			logger.error("ABOBA");
			subTaskRepository.save(
					new SubTask("sub task text", false, task)
			);
			Iterable<Task> tasks = taskRepository.findAll();
			for (Task taskEl : tasks) {
				logger.error(taskEl.toString());
			}
		};
	}

}
