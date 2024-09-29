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

public final class ProjectsTable implements DynamoDBTable {
    
    private final Stack stack;

    public ProjectsTable(Stack stack) {
        this.stack = stack;
    }

    public void create() {

        Table projectsTable = DynamoDBCore.buildTable(
                Table.Builder.create(stack, "ProjectsTable"),
                new TableBuilder("Projects", "userId", Optional.of("projectId")));

        projectsTable.addGlobalSecondaryIndex(
                DynamoDBCore.buildGlobalSecondaryIndex(new GlobalSecondaryIndex("gsi_status", "userId", Optional.of("status"))));
    }
}
