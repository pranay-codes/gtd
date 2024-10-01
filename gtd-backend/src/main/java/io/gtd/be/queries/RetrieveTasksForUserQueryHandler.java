package io.gtd.be.queries;

import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Qualifier("retrieveTasksForUserQueryHandler")
public final class RetrieveTasksForUserQueryHandler implements QueryHandler<UserId, List<Task>> {

    private final TaskRepository repository;

    public RetrieveTasksForUserQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> handle(UserId userId) {

        return repository.retrieveAllForUserId(userId).stream()
                .map(task -> new Task(task.getId(), task.getTitle(), task.getDetails(), task.getContext(), Priorities.valueOf(task.getPriority()), LocalDateTime.parse(task.getDueDate()), task.getStatus()))
                .toList();

    }


}
