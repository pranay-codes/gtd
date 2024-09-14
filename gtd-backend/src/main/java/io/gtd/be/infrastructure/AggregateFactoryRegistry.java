package io.gtd.be.infrastructure;

import io.gtd.be.aggregates.AddTaskAggregate;
import io.gtd.be.aggregates.factory.AggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AggregateFactoryRegistry {

    private final Map<Class<?>, AggregateFactory<?, ?>> factories;

    @Autowired
    public AggregateFactoryRegistry(Map<Class<?>, AggregateFactory<?, ?>> factories) {
        this.factories = factories;
        System.out.println("Factories map initialized with size: " + factories.size());
    }

    public void addFactory(Class<?> aggregate, AggregateFactory<?, ?> aggregateFactory) {
        factories.put(aggregate, aggregateFactory);
    }

    @SuppressWarnings("unchecked")
    public <TCommand, TAggregate> AggregateFactory<TCommand, TAggregate> getFactory(Class<TAggregate> aggregateClass) {
        return (AggregateFactory<TCommand, TAggregate>) factories.get(aggregateClass);
    }
}
