package io.gtd.be.aggregates.factory;

public interface AggregateFactory <TCommand, TAggregate> {
    TAggregate createFromCommand(TCommand command);
}
