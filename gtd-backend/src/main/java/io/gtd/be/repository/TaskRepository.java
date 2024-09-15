package io.gtd.be.repository;

import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.entities.Task;

import java.util.List;

public interface TaskRepository {
    
    void save(Task task);

    List<Task> findAllForUserId(UserId userId);
}
