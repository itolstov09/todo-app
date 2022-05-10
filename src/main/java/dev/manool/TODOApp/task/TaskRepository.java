package dev.manool.TODOApp.task;

import dev.manool.TODOApp.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
