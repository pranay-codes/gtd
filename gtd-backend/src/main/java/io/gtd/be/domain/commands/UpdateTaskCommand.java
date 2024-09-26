package io.gtd.be.domain.commands;

import io.gtd.be.domain.values.task.*;
import io.gtd.be.domain.values.user.UserId;
import io.gtd.be.entities.Task;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UpdateTaskCommand (Task task, String newStatus) {

}
