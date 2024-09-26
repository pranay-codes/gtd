package io.gtd.be.commands;

import io.gtd.be.domain.commands.UpdateTaskCommand;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("updateTaskStatusCommandHandler")
public final class UpdateTaskStatusCommandHandler implements CommandHandler<UpdateTaskCommand, TaskId> {

    private final TaskRepository taskRepository;

    public UpdateTaskStatusCommandHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskId handle(UpdateTaskCommand updateTaskCommand) {
        taskRepository.updateTask(updateTaskCommand.task().updateStatus(updateTaskCommand.newStatus()));
        return null;
    }
}
