package io.gtd.be.errorHandling.exception;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String taskId) {
        super(String.format("TaskId [%s] not found", taskId));
    }


}
