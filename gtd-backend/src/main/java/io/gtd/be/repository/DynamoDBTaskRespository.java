package io.gtd.be.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import io.gtd.be.domain.values.task.TaskId;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.entities.Task;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DynamoDBTaskRespository implements TaskRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoDBTaskRespository(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);


    }

    @Override
    public Task save(Task task) {
        dynamoDBMapper.save(task);
        return task;
    }

    @Override
    public List<Task> retrieveAllForUserId(UserId userId) {
        Task taskKey = Task.builder()
                .userId(userId.userId())
                .build();

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withIndexName("userId-index")
                .withConsistentRead(false)
                .withHashKeyValues(taskKey);

        return dynamoDBMapper.query(Task.class, queryExpression);

    }

    @Override
    public Optional<Task> retrieveById(TaskId taskId) {
        //        return Optional.ofNullable(dynamoDBMapper.load(Task.class, taskId.id()));
        Task taskKey = Task.builder()
                .id(taskId.id())
                .userId("userID-001")
                .build();

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withHashKeyValues(taskKey)
                .withConsistentRead(false);

        return dynamoDBMapper.query(Task.class, queryExpression).stream().findFirst();
    }

    public TaskId updateTask(Task task) {
        dynamoDBMapper.save(task,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id", new ExpectedAttributeValue(new AttributeValue().withS(task.getId())))
                        .withExpectedEntry("userId", new ExpectedAttributeValue(new AttributeValue().withS(task.getUserId()))));

        return new TaskId(task.getId());
    }
}
