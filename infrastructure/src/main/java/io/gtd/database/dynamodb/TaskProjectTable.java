package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

public final class TaskProjectTable implements DynamoDBTable {
    
    private final Stack stack;

    public TaskProjectTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        // Task-Project Table
        Table taskProjectTable = Table.Builder.create(stack, "TaskProjectTable")
            .tableName("TaskProject")
            .partitionKey(Attribute.builder()
                .name("projectId")
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

    }
}
