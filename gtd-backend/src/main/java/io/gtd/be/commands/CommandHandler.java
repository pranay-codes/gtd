package io.gtd.be.commands;

public sealed interface CommandHandler <T> permits AddTaskCommandHandler {
    
    void handle(T t);
}
