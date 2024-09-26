package io.gtd.be.service;

import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.queries.RetrieveTaskForTaskIdQueryHandler;
import io.gtd.be.queries.RetrieveTasksForUserQueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class TaskOrchestrationQueryService implements TaskQueryService {

    private final RetrieveTasksForUserQueryHandler retrieveTasksForUserQueryHandler;

    public TaskOrchestrationQueryService(RetrieveTasksForUserQueryHandler retrieveTasksForUserQueryHandler) {
        this.retrieveTasksForUserQueryHandler = retrieveTasksForUserQueryHandler;
    }

    @Override
    public List<Task> getTasks(String userId) {
        UserId user = new UserId(userId);
        return retrieveTasksForUserQueryHandler.handle(user);
    }

}
