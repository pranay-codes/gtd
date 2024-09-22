package io.gtd.be.queries;

public sealed interface QueryHandler <T, R> permits RetrieveTasksForUserQueryHandler {

    R handle(T t);

}
