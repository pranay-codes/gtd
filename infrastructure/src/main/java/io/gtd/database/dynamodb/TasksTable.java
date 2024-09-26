package io.gtd.database.dynamodb;

import io.gtd.core.DynamoDBCore;
import io.gtd.model.database.GlobalSecondaryIndex;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

public final class TasksTable implements DynamoDBTable {

    private final Stack stack;


    public TasksTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        // Tasks Table
        Table tasksTable = Table.Builder.create(stack, "TasksTable")
            .tableName("Tasks")
            .partitionKey(Attribute.builder()
                .name("id")
                .type(AttributeType.STRING)
                .build())
            .billingMode(BillingMode.PROVISIONED)
            .encryption(TableEncryption.AWS_MANAGED)
            .pointInTimeRecovery(true)
            .build();



        // Adding Global Secondary Indexes (GSIs)
        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_dueDate", "id", "dueDate")));

        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_context", "id", "context")));

        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_userid_context", "userId", "context")));


    }
    
}
