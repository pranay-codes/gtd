package io.gtd.database.dynamodb;

import io.gtd.core.DynamoDBCore;
import io.gtd.model.database.GlobalSecondaryIndex;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.*;

import java.util.Optional;

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
        usersTable.addGlobalSecondaryIndex(
                DynamoDBCore.buildGlobalSecondaryIndex(
                        new GlobalSecondaryIndex("gsi_users_email", "email", Optional.of("userId"))
                ));
    }
}
