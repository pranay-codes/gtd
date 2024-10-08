package io.gtd.be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.values.task.*;
import io.gtd.be.domain.values.user.UserId;
import io.gtd.be.infrastructure.annotations.ValidEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AddTaskRequest (
        @NotBlank(message = "userid required")
        String userId,

        @NotBlank(message = "Title cannot be blank")
        @Size(min = 3, max = 64, message = "Title length invalid")
        String title,

        String details,

        @NotBlank(message = "Context cannot be blank")
        String context,

        @ValidEnum(enumClass = Priorities.class, message = "Priority can be either LOW, MEDIUM, HIGH")
        String priority,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @FutureOrPresent(message = "Due date must be in the future or present")
        LocalDateTime dueDate,

        @NotBlank(message = "Status cannot be blank")
        String status){

    public AddTaskCommand addTaskCommand() {
        return AddTaskCommand.builder()
                .userId(new UserId(userId))
                .title(new Title(title))
                .details(new Details(details))
                .priority(new Priority(Priorities.valueOf(priority)))
                .context(new Context(context))
                .dueDate(new DueDate(dueDate.toString()))
                .status(new Status(status))
                .build();
    }
}
