package io.gtd.model.database;

import java.util.Optional;

public record TableBuilder(String tableName, String partitionKey, Optional<String> sortKey) {

    public TableBuilder(String tableName, String partitionKey) {
        this(tableName, partitionKey, Optional.empty());
    }
}
