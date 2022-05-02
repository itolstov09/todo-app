package dev.manool.TODOApp.repositories;

import dev.manool.TODOApp.tasks.Subtask;
import dev.manool.TODOApp.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    List<Subtask> findByTask(Task task);

    List<Subtask> findSubtasksByTaskId(Long taskId);
}
