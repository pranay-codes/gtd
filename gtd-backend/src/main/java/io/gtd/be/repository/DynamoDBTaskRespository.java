package io.gtd.be.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import io.gtd.be.domain.values.task.UserId;
import io.gtd.be.entities.Task;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.List;
import java.util.Map;

@Repository
public class DynamoDBTaskRespository implements TaskRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoDBTaskRespository(AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @Override
    public void save(Task task) {
        dynamoDBMapper.save(task);
    }

    @Override
    public List<Task> findAllForUserId(UserId userId) {
        Task taskKey = Task.builder()
                .userId(userId.userId())
                .build();

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withIndexName("userId-index")
                .withConsistentRead(false)
                .withHashKeyValues(taskKey);

        return dynamoDBMapper.query(Task.class, queryExpression);

    }
}
