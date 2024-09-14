package io.gtd.be.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.gtd.be.entities.Task;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDBTaskRespository implements TaskRepository {

    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDBMapper;

    public DynamoDBTaskRespository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @Override
    public void save(Task task) {
        dynamoDBMapper.save(task);
    }
}
