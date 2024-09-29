package io.gtd.be.repository;

import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    
    Task save(Task task);

    List<Task> retrieveAllForUserId(UserId userId);

    Optional<Task> retrieveById(TaskId taskId);

    TaskId updateTask(Task task);
}
