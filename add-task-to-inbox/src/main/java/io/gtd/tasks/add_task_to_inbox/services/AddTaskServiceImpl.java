package io.gtd.tasks.add_task_to_inbox.services;

import org.springframework.stereotype.Service;

import io.gtd.tasks.add_task_to_inbox.models.AddTaskRequest;

@Service
public class AddTaskServiceImpl implements AddTaskService {

    @Override
    public String addTask(AddTaskRequest task) {
        return new StringBuilder().append( "new task added: ")
            .append(task.details())
            .append(task.title())
            .toString();
    }
    
}
