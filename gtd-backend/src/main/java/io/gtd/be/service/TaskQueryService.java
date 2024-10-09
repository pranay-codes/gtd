package io.gtd.be.service;

import io.gtd.be.domain.models.Task;

import java.util.List;

public interface TaskQueryService {
    List<Task> getTasks(String userId);

}
