package io.gtd.be.service;

import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.queries.RetrieveTasksForUserQueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TaskQueryServiceImpl implements TaskQueryService {

    private final RetrieveTasksForUserQueryHandler retrieveTasksForUserQueryHandler;

    public TaskQueryServiceImpl(RetrieveTasksForUserQueryHandler retrieveTasksForUserQueryHandler) {
        this.retrieveTasksForUserQueryHandler = retrieveTasksForUserQueryHandler;
    }

    @Override
    public List<Task> getTasks(String userId) {
        UserId user = new UserId(userId);
        return retrieveTasksForUserQueryHandler.handle(user);
    }

}
