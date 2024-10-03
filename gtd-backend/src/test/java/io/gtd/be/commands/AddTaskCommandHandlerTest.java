package io.gtd.be.commands;

import io.gtd.be.domain.commands.AddTaskCommand;
import io.gtd.be.domain.enums.Priorities;
import io.gtd.be.domain.values.task.*;
import io.gtd.be.domain.values.user.UserId;
import io.gtd.be.entities.Task;
import io.gtd.be.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddTaskCommandHandlerTest {

    @Mock
    private TaskRepository taskRepository;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;

    @InjectMocks
    private AddTaskCommandHandler addTaskCommandHandler;

    private AddTaskCommand addTaskCommand;

    @BeforeEach
    void setUp() {
        addTaskCommand = new AddTaskCommand(new UserId("UserId-001"),
                new Title("Create Test Case"),
                new Details("Details of a test case"),
                new Priority(Priorities.HIGH),
                new Context("Office"),
                new DueDate("2024-10-14T10:17"),
                new Status("OPEN"));
    }

    @Test
    public void testHandle_savesTaskAndReturnsId() throws Exception {

        // Mock the behavior of taskRepository.save()
        Task mockTask = Task.builder()
                        .id("UNIQUE_ID")
                        .title("Create Test Case")
                        .build();

        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        // Call the handle method
        TaskId taskId = addTaskCommandHandler.handle(addTaskCommand);

        // Verify that the task was saved
        verify(taskRepository).save(taskCaptor.capture());

        // Assert that the returned TaskId matches the saved task's id
        Assertions.assertThat(taskCaptor.getValue().getTitle()).isEqualTo("Create Test Case");
    }
}
