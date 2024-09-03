package io.gtd.database.dynamodb;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class GettingThingsDoneDBStack extends Stack {
    
    public GettingThingsDoneDBStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public GettingThingsDoneDBStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        new UserTable(this).create();
        new TasksTable(this).create();
        new ProjectsTable(this).create();
        new ContextTable(this).create();
        new TaskProjectTable(this).create();
        new ArchivedTasksProjectTable(this).create();
        new SettingsTable(this).create();
    }

}
