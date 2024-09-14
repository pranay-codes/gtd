package io.gtd.be.commands;

public interface CommandHandler <T> {
    
    void handle(T t);
}
