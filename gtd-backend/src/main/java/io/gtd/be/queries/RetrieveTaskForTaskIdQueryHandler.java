package io.gtd.be.queries;

import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.entities.Task;
import io.gtd.be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Qualifier("retrieveTaskForTaskIdQueryHandler")
public final class RetrieveTaskForTaskIdQueryHandler implements QueryHandler<TaskId, Optional<Task>> {

    private final TaskRepository repository;
    public RetrieveTaskForTaskIdQueryHandler(@Autowired final TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> handle(TaskId taskId) {
        return repository.retrieveById(taskId);
    }
}
