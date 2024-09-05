package io.gtd.tasks.add_task_to_inbox.services;

import io.gtd.tasks.add_task_to_inbox.models.AddTaskRequest;

public interface AddTaskService {
    
    public String addTask(AddTaskRequest task);
}
