package com.spring_app.sandbox.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

    private final UUID id;

    public TaskNotFoundException(UUID id) {
        super(String.format("Task with '%s' does not exist.", id));
        this.id = id;
    }

    public UUID getId() {
        return id;
    }   
    
}
