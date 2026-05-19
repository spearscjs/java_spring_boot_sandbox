package com.spring_app.sandbox.domain;

import java.time.LocalDate;

import com.spring_app.sandbox.domain.entity.TaskPriority;
import com.spring_app.sandbox.domain.entity.TaskStatus; 

public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority
) {
}