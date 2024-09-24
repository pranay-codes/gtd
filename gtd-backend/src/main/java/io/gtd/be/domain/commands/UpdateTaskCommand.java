package io.gtd.be.domain.commands;

import io.gtd.be.domain.values.task.*;
import io.gtd.be.domain.values.user.UserId;
import io.gtd.be.entities.Task;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UpdateTaskCommand (UserId userId,
                                 TaskId taskId,
                                 Title title,
                                 Details details,
                                 Context context,
                                 DueDate dueDate,
                                 Priority priority,
                                 Status status,
                                 CreatedAt createdAt) {

    public Task toEntity() {
        return Task.builder()
                .id(this.taskId.id())
                .userId(this.userId.userId())
                .title(this.title().title())
                .context(this.context().context())
                .details(this.details().details())
                .dueDate(this.dueDate().dueDate())
                .priority(this.priority().priority())
                .status(this.status().status())
                .createdAt(this.createdAt.createdAt())
                .updatedAt(Instant.now().toString())
                .build();
    }
}
