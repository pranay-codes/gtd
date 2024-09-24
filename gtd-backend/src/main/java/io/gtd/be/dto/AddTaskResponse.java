package io.gtd.be.dto;

public record AddTaskResponse (
        String taskId,
        String status,
        String message
) {
}
