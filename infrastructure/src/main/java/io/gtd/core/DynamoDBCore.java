package io.gtd.core;

import io.gtd.model.database.GlobalSecondaryIndex;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;

public class DynamoDBCore {

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