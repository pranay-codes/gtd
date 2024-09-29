package io.gtd.database.dynamodb;

import io.gtd.core.DynamoDBCore;
import io.gtd.model.database.TableBuilder;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableEncryption;

import java.util.Optional;

public final class ContextTable implements DynamoDBTable {
    
    private final Stack stack;

    public ContextTable (Stack stack) {
        this.stack = stack;
    }
    public void create() {
        Table contextsTable = DynamoDBCore.buildTable(
                Table.Builder.create(stack, "ContextsTable"),
                new TableBuilder("Contexts", "userId", Optional.of("contextId")));
    }
}
