package io.gtd.tasks.add_task_to_inbox.functions;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import io.gtd.tasks.add_task_to_inbox.models.AddTaskRequest;
import io.gtd.tasks.add_task_to_inbox.services.AddTaskService;

public class AddTask implements Function<AddTaskRequest, String> {

    @Autowired
    private final AddTaskService addTaskService;

    public AddTask(AddTaskService addTaskService) {
        this.addTaskService = addTaskService;
    }
    

    @Override
    public String apply(AddTaskRequest t) {
        return addTaskService.addTask(t);
    }
    
}
