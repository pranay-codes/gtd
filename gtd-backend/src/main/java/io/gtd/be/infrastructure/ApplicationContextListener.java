package io.gtd.be.infrastructure;

import io.gtd.be.aggregates.AddTaskAggregate;
import io.gtd.be.aggregates.factory.AggregateFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AggregateFactoryRegistry registry;

    @PostConstruct
    public void inspect() {
        String[] beanNames = applicationContext.getBeanNamesForType(AggregateFactory.class);
        System.out.println("Registered AggregateFactoryRegistry Beans:");
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}

