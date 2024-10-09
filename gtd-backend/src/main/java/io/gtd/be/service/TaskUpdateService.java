package io.gtd.be.service;

import io.gtd.be.errorHandling.exception.TaskNotFoundException;

public interface TaskUpdateService {

    String markTaskAsComplete(String taskId) throws TaskNotFoundException;
}
