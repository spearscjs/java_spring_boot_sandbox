package com.spring_app.sandbox.domain;

import java.time.LocalDate;

import com.spring_app.sandbox.domain.entity.TaskPriority;

public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {
    
}
