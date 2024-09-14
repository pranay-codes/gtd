package io.gtd.be.aggregates;

import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.entities.Task;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Getter
@Component
public class AddTaskAggregate implements Aggregate<AddTaskCommand> {

    private Task task;
    @Override
    public void handle(AddTaskCommand addTaskCommand) {

        task = Task.builder()
                .taskId(UUID.randomUUID().toString())
                .title(addTaskCommand.title().title())
                .context(addTaskCommand.context().context())
                .details(addTaskCommand.details().details())
                .dueDate(addTaskCommand.dueDate().dueDate())
                .priority(addTaskCommand.priority().priority())
                .status(addTaskCommand.status().status())
                .createdAt(Instant.now().toString())
                .updatedAt(Instant.now().toString())
                .build();
    }

}
