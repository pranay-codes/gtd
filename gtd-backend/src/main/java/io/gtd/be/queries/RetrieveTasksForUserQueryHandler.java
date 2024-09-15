package io.gtd.be.queries;

import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrieveTasksForUserQueryHandler implements QueryHandler<UserId, List<Task>> {

    private final TaskRepository repository;

    public RetrieveTasksForUserQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> handle(UserId userId) {

        return repository.findAllForUserId(userId).stream()
                .map(task -> new Task(task.getTitle(), task.getDetails(), task.getContext(), task.getPriority(), LocalDateTime.parse(task.getDueDate()), task.getStatus()))
                .toList();

    }


}
