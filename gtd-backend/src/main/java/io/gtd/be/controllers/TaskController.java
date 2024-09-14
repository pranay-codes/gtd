package io.gtd.be.controllers;

import io.gtd.be.commands.AddTaskCommandHandler;
import io.gtd.be.domain.models.Task;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    private final AddTaskCommandHandler addTaskCommandHandler;

    public TaskController(AddTaskCommandHandler addTaskCommandHandler) {
        this.addTaskCommandHandler = addTaskCommandHandler;
    }

    @PostMapping(path = "/v1")
    public ResponseEntity<String> addTask(@Valid @RequestBody Task task) {
        log.info("Adding task: {}", task.title());
        addTaskCommandHandler.handle(task.addTaskCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully");
    }
}
