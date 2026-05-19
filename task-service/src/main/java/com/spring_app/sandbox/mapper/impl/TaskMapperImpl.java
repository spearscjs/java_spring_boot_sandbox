package com.spring_app.sandbox.mapper.impl;

import org.springframework.stereotype.Component;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.dto.CreateTaskRequestDto;
import com.spring_app.sandbox.domain.dto.TaskDto;
import com.spring_app.sandbox.domain.dto.UpdateTaskRequestDto;
import com.spring_app.sandbox.domain.entity.Task;
import com.spring_app.sandbox.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto createTaskRequestDto) {
        return new CreateTaskRequest(
            createTaskRequestDto.title(),
            createTaskRequestDto.description(),
            createTaskRequestDto.dueDate(),
            createTaskRequestDto.priority()
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus()
        );
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(
            dto.title(),
            dto.description(),
            dto.dueDate(),
            dto.status(),
            dto.priority()
        );
    }


}
