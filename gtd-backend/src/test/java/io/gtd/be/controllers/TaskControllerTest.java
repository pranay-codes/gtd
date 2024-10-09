package io.gtd.be.controllers;

import io.gtd.be.commands.AddTaskCommandHandler;
import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.models.Task;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.errorHandling.exception.TaskNotFoundException;
import io.gtd.be.service.TaskQueryService;
import io.gtd.be.service.TaskUpdateService;
import io.gtd.be.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
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

    @MockBean
    private TaskUpdateService taskUpdateService;


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

    @Test
    public void givenValidUserId_whenTaskQeuryReturnsEmptyList_thenReturnEmptyTaskList() throws Exception {
        String userId = "user123";
        when(taskQueryService.getTasks(userId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tasks/v1/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void givenValidTaskId_whenMarkTaskAsComplete_thenReturnTaskMarkedAsComplete() throws Exception {
        String taskId = "task123";
        String expectedResponse = "Task marked as complete";

        // Mocking the service response
        when(taskUpdateService.markTaskAsComplete(taskId)).thenReturn(expectedResponse);

        // Perform the PUT request and validate response
        mockMvc.perform(put("/tasks/v1/complete/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andExpect(jsonPath("$").value(expectedResponse));

        // Verify that the service method was called with the correct taskId
        verify(taskUpdateService).markTaskAsComplete(taskId);
    }

    @Test
    public void givenValidTaskId_whenTaskNotFound_thenReturnTaskNotFound() throws Exception {
        String taskId = "invalidTaskId";
        when(taskUpdateService.markTaskAsComplete(taskId)).thenThrow(new TaskNotFoundException(taskId));

        mockMvc.perform(put("/tasks/v1/complete/{taskId}", taskId))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("TaskId [invalidTaskId] not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("TaskId [invalidTaskId] not found"));    }


}
