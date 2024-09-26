package io.gtd.be.service;

import io.gtd.be.domain.models.Task;

import java.util.List;

public sealed interface TaskQueryService permits TaskQueryServiceImpl {
    List<Task> getTasks(String userId);

}
