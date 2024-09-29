package io.gtd.model.database;

import java.util.Optional;

public record GlobalSecondaryIndex(String indexName, String partitionKey, Optional<String> sortKey) {

    public GlobalSecondaryIndex(String indexName, String partitionKey) {
        this(indexName, partitionKey, Optional.empty());
    }
}
