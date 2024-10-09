package io.gtd.be.service;

import io.gtd.be.commands.CommandHandler;
import io.gtd.be.domain.commands.UpdateTaskCommand;
import io.gtd.be.domain.values.task.*;
import io.gtd.be.entities.Task;
import io.gtd.be.errorHandling.exception.TaskNotFoundException;
import io.gtd.be.queries.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class TaskUpdateServiceImpl implements  TaskUpdateService {

    private String COMPLETE = "Task [%s] successfully updated to COMPLETE";

    private final QueryHandler<TaskId, Optional<Task>> queryHandler;
    private final CommandHandler<UpdateTaskCommand, TaskId> commandHandler;

    @Autowired
    public TaskUpdateServiceImpl(QueryHandler<TaskId, Optional<Task>> queryHandler,
                                 @Qualifier("updateTaskStatusCommandHandler") CommandHandler<UpdateTaskCommand, TaskId> commandHandler) {
        this.queryHandler = queryHandler;
        this.commandHandler = commandHandler;
    }

    public String markTaskAsComplete(String id) throws TaskNotFoundException {
        // retrieve task for taskid;
        TaskId taskId = new TaskId(id);
        Optional<Task> task = queryHandler.handle(taskId);

        if (task.isEmpty()) {
            throw new TaskNotFoundException(id);
        }

        System.out.println("Task present - " + task.get().getTitle());

        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(task.get(), "COMPLETE");
        commandHandler.handle(updateTaskCommand);

        // update task as done
        // if successful return successful message
        return String.format(COMPLETE, task.get().getTitle());
    }
}
