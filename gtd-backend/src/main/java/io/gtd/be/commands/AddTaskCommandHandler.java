package io.gtd.be.commands;

import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.infrastructure.AggregateFactoryRegistry;
import io.gtd.be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class AddTaskCommandHandler implements CommandHandler<AddTaskCommand> {

    private final TaskRepository taskRepository;
    private final AggregateFactoryRegistry factoryRegistry;

    @Autowired
    public AddTaskCommandHandler(TaskRepository taskRepository, AggregateFactoryRegistry factoryRegistry) {
        this.taskRepository = taskRepository;
        this.factoryRegistry = factoryRegistry;
    }

    @Override
    public void handle(AddTaskCommand addTask) {

        taskRepository.save(addTask.toEntity());

    }
    
}
