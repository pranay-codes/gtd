package io.gtd.be.aggregates.factory;

import io.gtd.be.aggregates.AddTaskAggregate;
import io.gtd.be.domain.commands.AddTaskCommand;
import org.springframework.stereotype.Component;

@Component
public class AddTaskAggregateFactory implements AggregateFactory<AddTaskCommand, AddTaskAggregate> {
    @Override
    public AddTaskAggregate createFromCommand(AddTaskCommand addTaskCommand) {
        AddTaskAggregate addTaskAggregate = new AddTaskAggregate();
        addTaskAggregate.handle(addTaskCommand);
        return addTaskAggregate;
    }
}
