package io.gtd.core;

import io.gtd.model.database.GlobalSecondaryIndex;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.GlobalSecondaryIndexProps;

public class DynamoDBCore {

    public static GlobalSecondaryIndexProps buildGlobalSecondaryIndex(GlobalSecondaryIndex globalSecondaryIndex) {
        return GlobalSecondaryIndexProps.builder()
                .indexName(globalSecondaryIndex.indexName())
                .partitionKey(Attribute.builder()
                        .name(globalSecondaryIndex.partitionKey())
                        .type(AttributeType.STRING)
                        .build())
                .sortKey(Attribute.builder()
                        .name(globalSecondaryIndex.sortKey())
                        .type(AttributeType.STRING)
                        .build())
                .build();
    }}
