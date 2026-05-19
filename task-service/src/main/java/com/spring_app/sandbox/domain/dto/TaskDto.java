package com.spring_app.sandbox.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.spring_app.sandbox.domain.entity.TaskPriority;
import com.spring_app.sandbox.domain.entity.TaskStatus;

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority,
    TaskStatus status
) {

}
