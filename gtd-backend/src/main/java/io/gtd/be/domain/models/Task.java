package io.gtd.be.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.values.task.Context;
import io.gtd.be.domain.values.task.Details;
import io.gtd.be.domain.values.task.DueDate;
import io.gtd.be.domain.values.task.Priority;
import io.gtd.be.domain.values.task.Status;
import io.gtd.be.domain.values.task.Title;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

public record Task(
        @NotBlank(message = "Title cannot be blank")
        String title,

        String details,

        @NotBlank(message = "Context cannot be blank")
        String context,

        String priority,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @FutureOrPresent(message = "Due date must be in the future")
        LocalDateTime dueDate,

        @NotBlank(message = "Status cannot be blank")
        String status) {
    
    public AddTaskCommand addTaskCommand() {
        return AddTaskCommand.builder()
            .title(new Title(title))
            .details(new Details(details))
            .priority(new Priority(priority))
            .context(new Context(context))
            .dueDate(new DueDate(dueDate.toString()))
            .status(new Status(status))
            .build();
    }
}
