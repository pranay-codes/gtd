package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

public final class ProjectsTable implements DynamoDBTable {
    
    private final Stack stack;

    public ProjectsTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        Table projectsTable = Table.Builder.create(stack, "ProjectsTable")
            .tableName("Projects")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("projectId")
                .type(AttributeType.STRING)
                .build())
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .encryption(TableEncryption.AWS_MANAGED)
            .pointInTimeRecovery(true)
            .build();

        // Adding Global Secondary Index (GSI) for Status
        projectsTable.addGlobalSecondaryIndex(GlobalSecondaryIndexProps.builder()
            .indexName("gsi_status")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("status")
                .type(AttributeType.STRING)
                .build())
            .build());

    }
}
