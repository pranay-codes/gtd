package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

public class TasksTable implements DynamoDBTable {

    private final Stack stack;


    public TasksTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        // Tasks Table
        Table tasksTable = Table.Builder.create(stack, "TasksTable")
            .tableName("Tasks")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("taskId")
                .type(AttributeType.STRING)
                .build())
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .encryption(TableEncryption.AWS_MANAGED)
            .pointInTimeRecovery(true)
            .build();

        // Adding Global Secondary Indexes (GSIs)
        tasksTable.addGlobalSecondaryIndex(GlobalSecondaryIndexProps.builder()
            .indexName("gsi_dueDate")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("dueDate")
                .type(AttributeType.STRING)
                .build())
            .build());

        tasksTable.addGlobalSecondaryIndex(GlobalSecondaryIndexProps.builder()
            .indexName("gsi_context")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("context")
                .type(AttributeType.STRING)
                .build())
            .build());

    }
    
}
