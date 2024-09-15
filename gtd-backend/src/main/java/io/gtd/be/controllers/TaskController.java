package io.gtd.be.controllers;

import io.gtd.be.commands.AddTaskCommandHandler;
import io.gtd.be.domain.models.Task;
import io.gtd.be.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final AddTaskCommandHandler addTaskCommandHandler;
    private final TaskService taskService;

    public TaskController(AddTaskCommandHandler addTaskCommandHandler, TaskService taskService) {
        this.addTaskCommandHandler = addTaskCommandHandler;
        this.taskService = taskService;
    }

    @PostMapping(path = "/v1")
    public ResponseEntity<String> addTask(@Valid @RequestBody Task task) {
        log.info("Adding task: {}", task.title());
        addTaskCommandHandler.handle(task.addTaskCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully");
    }

    @GetMapping(path = "/v1/{userId}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable String userId) {
        return ResponseEntity.ok(this.taskService.getTasks(userId));
    }
}
