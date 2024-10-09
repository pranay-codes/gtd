package io.gtd.be.controllers;

import io.gtd.be.commands.AddTaskCommandHandler;
import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.dto.AddTaskRequest;
import io.gtd.be.dto.AddTaskResponse;
import io.gtd.be.service.TaskQueryService;
import io.gtd.be.service.TaskUpdateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final AddTaskCommandHandler addTaskCommandHandler;
//    private final TaskUpdateService taskUpdateService;
    private final TaskQueryService taskQueryService;

    public TaskController(AddTaskCommandHandler addTaskCommandHandler, TaskQueryService taskQueryService) {
        this.addTaskCommandHandler = addTaskCommandHandler;
        this.taskQueryService = taskQueryService;
//        this.taskUpdateService = taskUpdateService;
    }

    @PostMapping(path = "/v1")
    public ResponseEntity<AddTaskResponse> addTask(@Valid @RequestBody AddTaskRequest task) {
        log.info("Adding task: {}", task.title());
        TaskId taskId = addTaskCommandHandler.handle(task.addTaskCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddTaskResponse(taskId.id(), "SUCCESS", "New task successfully added"));
    }

    @GetMapping(path = "/v1/{userId}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable String userId) {
        return ResponseEntity.ok(this.taskQueryService.getTasks(userId));
    }
//
//    @PutMapping(path = "/v1/complete/{taskId}")
//    public ResponseEntity<String> completeTask(@PathVariable String taskId) {
//        log.info("complete task: {}", taskId);
//
//        return ResponseEntity.ok(taskUpdateService.markTaskAsComplete(taskId));
//    }


}
