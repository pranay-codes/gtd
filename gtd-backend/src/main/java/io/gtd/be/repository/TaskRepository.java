package io.gtd.be.repository;

import io.gtd.be.entities.Task;

public interface TaskRepository {
    
    void save(Task task);
}
