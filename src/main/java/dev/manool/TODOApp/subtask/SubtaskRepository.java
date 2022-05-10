package dev.manool.TODOApp.subtask;

import dev.manool.TODOApp.subtask.Subtask;
import dev.manool.TODOApp.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    List<Subtask> findByTask(Task task);

    List<Subtask> findSubtasksByTaskId(Long taskId);
}
