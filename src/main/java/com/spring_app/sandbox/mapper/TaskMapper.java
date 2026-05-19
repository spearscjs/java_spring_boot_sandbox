package com.spring_app.sandbox.mapper;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.dto.CreateTaskRequestDto;
import com.spring_app.sandbox.domain.dto.TaskDto;
import com.spring_app.sandbox.domain.dto.UpdateTaskRequestDto;
import com.spring_app.sandbox.domain.entity.Task;

public interface TaskMapper {

    // dto to create task request, this is what the controller will use to convert the request body to a create task request object, 
    // which is what the service layer expects
    CreateTaskRequest fromDto(CreateTaskRequestDto createTaskRequestDto);

    // entity to dto, this is what the controller will use to convert the task entity to a task dto for the response
    TaskDto toDto(Task task);
    
    UpdateTaskRequest fromDto(UpdateTaskRequestDto updateTaskRequestDto);
}
