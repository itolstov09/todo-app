package dev.manool.TODOApp.repositories;

import dev.manool.TODOApp.tasks.SubTask;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubTaskRepository extends CrudRepository<SubTask, Long> {
    List<SubTask> findByTask(Task task);
}
