package io.gtd.be.controllers;

import io.gtd.be.commands.AddTaskCommandHandler;
import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.dto.AddTaskRequest;
import io.gtd.be.service.TaskQueryService;
import io.gtd.be.service.TaskQueryServiceImpl;
import io.gtd.be.service.TaskUpdateService;
import io.gtd.be.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddTaskCommandHandler addTaskCommandHandler;

    @MockBean
    private TaskQueryService taskQueryService;


    @Test
    public void givenValidTaskRequest_whenAddTask_thenReturnSuccess() throws Exception {

        TaskId mockId = new TaskId(UUID.randomUUID().toString());

        when(addTaskCommandHandler.handle(any(AddTaskCommand.class)))
                .thenReturn(mockId);

        mockMvc.perform(post("/tasks/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\n" +
                        "{\n" +
                        "    \"title\": \"call John to book holiday care\",\n" +
                        "    \"userId\": \"userID-001\",\n" +
                        "    \"details\": \"Reeya needs to go for holiday care\",\n" +
                        "    \"context\": \"Reeya\",\n" +
                        "    \"priority\": \"HIGH\",\n" +
                        "    \"dueDate\": \"2024-10-14T10:17\",\n" +
                        "    \"status\": \"OPEN\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskId").value(mockId.id()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("New task successfully added"));

        verify(addTaskCommandHandler).handle(any(AddTaskCommand.class));


    }

    @Test
    public void givenRequestWithMissingUserId_whenAddTask_thenReturnBadRequest() throws Exception {

        mockMvc.perform(post("/tasks/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\n" +
                                "{\n" +
                                "    \"title\": \"call John to book holiday care\",\n" +
                                "    \"details\": \"Reeya needs to go for holiday care\",\n" +
                                "    \"context\": \"Reeya\",\n" +
                                "    \"priority\": \"HIGH\",\n" +
                                "    \"dueDate\": \"2024-10-14T10:17\",\n" +
                                "    \"status\": \"OPEN\"\n" +
                                "}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Request Validation Failed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("userId: userid required"));


    }

    @Test
    public void givenValidUserId_whenGettingTasks_thenReturnTaskList() throws Exception {
        String userId = "userID-001";
        Task task1 = new Task(UUID.randomUUID().toString(),
                "create task",
                "create a task api",
                "Dev",
                Priorities.HIGH,
                LocalDateTime.now(),
                "CREATED");

        Task task2 = new Task(UUID.randomUUID().toString(),
                "get all tasks",
                "Retrieve a list of all tasks",
                "Dev",
                Priorities.HIGH,
                LocalDateTime.now(),
                "CREATED");

        List<Task> mockTaskList = Arrays.asList(task1, task2);
        when(taskQueryService.getTasks(userId)).thenReturn(mockTaskList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/v1/{userId}", userId)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(JsonUtil.toJson(mockTaskList)));

        verify(taskQueryService).getTasks(userId);
    }
}
