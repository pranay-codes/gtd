package io.gtd.database.dynamodb;

import io.gtd.core.DynamoDBCore;
import io.gtd.model.database.GlobalSecondaryIndex;
import io.gtd.model.database.TableBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

import java.util.Optional;

public final class TasksTable implements DynamoDBTable {

    private final Stack stack;


    public TasksTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        // Tasks Table
        Table tasksTable = DynamoDBCore.buildTable(Table.Builder
                .create(stack, "TasksTable"),
                new TableBuilder("Tasks", "id"));

        // Adding Global Secondary Indexes (GSIs)
        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_dueDate", "id", Optional.of("dueDate"))));

        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_context", "id", Optional.of("context"))));

        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_userid_context", "userId", Optional.of("context"))));

        tasksTable.addGlobalSecondaryIndex(DynamoDBCore
                .buildGlobalSecondaryIndex(new GlobalSecondaryIndex("userId-index", "userId")));


    }
    
}
