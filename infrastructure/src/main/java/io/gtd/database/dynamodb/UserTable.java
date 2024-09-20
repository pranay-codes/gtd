package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.*;

public final class UserTable implements DynamoDBTable {

    private final Stack stack;
    
    public UserTable (Stack stack) {
        this.stack = stack;
    }

    public void create() {
        
        // Users Table
        Table usersTable = Table.Builder.create(stack, "UsersTable")
            .tableName("Users")
            .partitionKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .encryption(TableEncryption.AWS_MANAGED)
            .pointInTimeRecovery(true)
            .build();

        // Adding Global Secondary Index (GSI) for Email
        usersTable.addGlobalSecondaryIndex(GlobalSecondaryIndexProps.builder()
            .indexName("gsi_users_email")
            .partitionKey(Attribute.builder()
                .name("email")
                .type(AttributeType.STRING)
                .build())
            .sortKey(Attribute.builder()
                .name("userId")
                .type(AttributeType.STRING)
                .build())
            .build());
    }
}
