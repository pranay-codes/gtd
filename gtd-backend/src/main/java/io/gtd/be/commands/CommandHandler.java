package io.gtd.be.commands;

public sealed interface CommandHandler <T, R> permits AddTaskCommandHandler, UpdateTaskStatusCommandHandler {
    
    R handle(T t);
}
