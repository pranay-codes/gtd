package io.gtd.core;

import io.gtd.model.database.TableBuilder;
import io.gtd.model.database.GlobalSecondaryIndex;
import software.amazon.awscdk.services.dynamodb.*;

public class DynamoDBCore {

    public static Table buildTable(Table.Builder table, TableBuilder tableBuilder) {

        table = table
                .tableName(tableBuilder.tableName())
                .partitionKey(Attribute.builder()
                        .name(tableBuilder.partitionKey())
                        .type(AttributeType.STRING)
                        .build());

        if (tableBuilder.sortKey().isPresent()) {
            table.sortKey(Attribute.builder()
                    .name(tableBuilder.sortKey().get())
                    .type(AttributeType.STRING)
                    .build());
        }

        table.billingMode(BillingMode.PROVISIONED)
                .encryption(TableEncryption.AWS_MANAGED)
                .pointInTimeRecovery(true);

        return table.build();
    }

    public static GlobalSecondaryIndexProps buildGlobalSecondaryIndex(GlobalSecondaryIndex globalSecondaryIndex) {
        GlobalSecondaryIndexProps.Builder globalSecondaryIndexPropsBuilder = GlobalSecondaryIndexProps.builder()
                .indexName(globalSecondaryIndex.indexName());

        globalSecondaryIndexPropsBuilder.partitionKey(Attribute.builder()
                .name(globalSecondaryIndex.partitionKey())
                .type(AttributeType.STRING)
                .build());

        if (globalSecondaryIndex.sortKey().isPresent()) {
            globalSecondaryIndexPropsBuilder.sortKey(Attribute.builder()
                    .name(globalSecondaryIndex.sortKey().get())
                    .type(AttributeType.STRING)
                    .build());
        }

        return globalSecondaryIndexPropsBuilder.build();
    }
}