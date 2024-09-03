package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

public class ContextTable implements DynamoDBTable {
    
    private final Stack stack;

    public ContextTable (Stack stack) {
        this.stack = stack;
    }
    public void create() {
        Table contextsTable = Table.Builder.create(stack, "ContextsTable")
            .tableName("Contexts")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("contextId")
                .type(AttributeType.STRING)
                .build())
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .encryption(TableEncryption.AWS_MANAGED)
            .pointInTimeRecovery(true)
            .build();

    }
}
