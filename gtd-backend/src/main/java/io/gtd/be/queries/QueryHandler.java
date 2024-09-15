package io.gtd.be.queries;

public interface QueryHandler <T, R> {

    R handle(T t);

}
