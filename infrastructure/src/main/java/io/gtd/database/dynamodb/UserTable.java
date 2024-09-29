package io.gtd.database.dynamodb;

import io.gtd.core.DynamoDBCore;
import io.gtd.model.database.GlobalSecondaryIndex;
import io.gtd.model.database.TableBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.*;

import java.util.Optional;

public final class UserTable implements DynamoDBTable {

    private final Stack stack;
    
    public UserTable (Stack stack) {
        this.stack = stack;
    }

    public void create() {

        Table usersTable = DynamoDBCore.buildTable(Table.Builder
                        .create(stack, "UsersTable"),
                new TableBuilder("Users", "userId"));

        // Adding Global Secondary Index (GSI) for Email
        usersTable.addGlobalSecondaryIndex(
                DynamoDBCore.buildGlobalSecondaryIndex(
                        new GlobalSecondaryIndex("gsi_users_email", "email", Optional.of("userId"))
                ));
    }
}
