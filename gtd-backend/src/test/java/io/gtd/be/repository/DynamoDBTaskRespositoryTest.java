package io.gtd.be.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import io.gtd.be.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DynamoDBTaskRespositoryTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private AmazonDynamoDB amazonDynamoDB;

    @Mock
    private DynamoDBMapperConfig dynamoDBMapperConfig;

    private DynamoDBTaskRespository dynamoDBTaskRespository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        dynamoDBTaskRespository = new DynamoDBTaskRespository(amazonDynamoDB, dynamoDBMapperConfig);

        // Mock DynamoDBMapper's save method
        doNothing().when(dynamoDBMapper).save(any(Task.class));

    }

    void givenValidTaskEntityThanSave() {
        Task task = Task.builder()
                .id("task-001")
                .title("new task")
                .build();

        // Create a mock UpdateItemResult
        UpdateItemResult updateItemResult = new UpdateItemResult();
        when(amazonDynamoDB.updateItem(any(UpdateItemRequest.class))).thenReturn(updateItemResult);
        dynamoDBTaskRespository.save(task);

        verify(dynamoDBMapper).save(task);
    }

}