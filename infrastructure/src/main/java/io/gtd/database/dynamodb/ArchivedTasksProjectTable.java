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

public final class ArchivedTasksProjectTable implements DynamoDBTable {

    private final Stack stack;

    public ArchivedTasksProjectTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {
        Table archivedTasksProjectsTable = DynamoDBCore.buildTable(
                Table.Builder.create(stack, "ArchivedTasksProjectsTable"),
                new TableBuilder("ArchivedTasksProjects", "userId", Optional.of("archivedItemId")));
    }
    
}
