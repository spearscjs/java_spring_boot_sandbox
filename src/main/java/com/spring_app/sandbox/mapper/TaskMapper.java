package com.spring_app.sandbox.mapper;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.dto.CreateTaskRequestDto;
import com.spring_app.sandbox.domain.dto.TaskDto;
import com.spring_app.sandbox.domain.dto.UpdateTaskRequestDto;
import com.spring_app.sandbox.domain.entity.Task;

public interface TaskMapper {

    CreateTaskRequest fromDto(CreateTaskRequestDto createTaskRequestDto);

    TaskDto toDto(Task task);
    
    UpdateTaskRequest fromDto(UpdateTaskRequestDto updateTaskRequestDto);
}
