package io.gtd.be.aggregates;

public interface Aggregate <T> {
    
    void handle(T t);
}
