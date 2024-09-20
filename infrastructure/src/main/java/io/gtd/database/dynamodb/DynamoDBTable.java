package io.gtd.database.dynamodb;

public sealed interface DynamoDBTable permits TasksTable, ContextTable, ProjectsTable, SettingsTable,
        ArchivedTasksProjectTable, TaskProjectTable, UserTable {
    
    public void create();
}
