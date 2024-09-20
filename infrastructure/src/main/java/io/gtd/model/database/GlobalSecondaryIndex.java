package io.gtd.model.database;

public record GlobalSecondaryIndex(String indexName, String partitionKey, String sortKey) {
}
