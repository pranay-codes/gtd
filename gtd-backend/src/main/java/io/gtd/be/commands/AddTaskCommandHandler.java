package io.gtd.be.commands;

import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.entities.Task;
import io.gtd.be.infrastructure.AggregateFactoryRegistry;
import io.gtd.be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("addTaskCommandHandler")
public final class AddTaskCommandHandler implements CommandHandler<AddTaskCommand, TaskId> {

    private final TaskRepository taskRepository;
    private final AggregateFactoryRegistry factoryRegistry;

    @Autowired
    public AddTaskCommandHandler(TaskRepository taskRepository, AggregateFactoryRegistry factoryRegistry) {
        this.taskRepository = taskRepository;
        this.factoryRegistry = factoryRegistry;
    }

    @Override
    public TaskId handle(AddTaskCommand addTask) {
        Task newTask = taskRepository.save(addTask.toEntity());
        return new TaskId(newTask.getId());
    }
    
}
