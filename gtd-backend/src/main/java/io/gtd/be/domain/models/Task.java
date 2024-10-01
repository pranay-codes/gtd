package io.gtd.be.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.commands.UpdateTaskCommand;
import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.values.task.*;
import io.gtd.be.domain.values.user.UserId;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

public record Task(

        String id,

        @NotBlank(message = "Title cannot be blank")
        @Size(min = 3, max = 64)
        String title,

        String details,

        @NotBlank(message = "Context cannot be blank")
        String context,

        Priorities priority,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @FutureOrPresent(message = "Due date must be in the future")
        LocalDateTime dueDate,

        @NotBlank(message = "Status cannot be blank")
        String status) {
    
}
