package io.gtd.be.domain.commands;

import io.gtd.be.domain.values.task.Context;
import io.gtd.be.domain.values.task.Details;
import io.gtd.be.domain.values.task.DueDate;
import io.gtd.be.domain.values.task.Priority;
import io.gtd.be.domain.values.task.Status;
import io.gtd.be.domain.values.task.Title;
import io.gtd.be.entities.Task;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AddTaskCommand(Title title, Details details, Priority priority, Context context, DueDate dueDate, Status status) {

    public Task toEntity() {
        return Task.builder()
                .userId("userID-001")
                .title(this.title().title())
                .context(this.context().context())
                .details(this.details().details())
                .dueDate(this.dueDate().dueDate())
                .priority(this.priority().priority())
                .status(this.status().status())
                .createdAt(Instant.now().toString())
                .build();
    }
}
