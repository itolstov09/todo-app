package dev.manool.TODOApp.repositories;

import dev.manool.TODOApp.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
