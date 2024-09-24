package io.gtd.be.queries;

public sealed interface QueryHandler <T, R> permits RetrieveTasksForUserQueryHandler, RetrieveTaskForTaskIdQueryHandler {

    R handle(T t);

}
