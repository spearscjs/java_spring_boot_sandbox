package com.spring_app.sandbox.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.dto.CreateTaskRequestDto;
import com.spring_app.sandbox.domain.dto.TaskDto;
import com.spring_app.sandbox.domain.dto.UpdateTaskRequestDto;
import com.spring_app.sandbox.domain.entity.Task;
import com.spring_app.sandbox.mapper.TaskMapper;
import com.spring_app.sandbox.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
        @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto
    ) {
            CreateTaskRequest createTaskRequest = taskMapper.fromDto(createTaskRequestDto);
            Task task = taskService.createTask(createTaskRequest);
            TaskDto taskDto = taskMapper.toDto(task);
            return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        List<Task> tasks = taskService.listTasks();
        List<TaskDto> taskDtos;
        taskDtos = tasks.stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(taskDtos);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
        @PathVariable UUID taskId,
        @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto
    ) {
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDto(updateTaskRequestDto);
        Task task = taskService.updateTask(taskId, updateTaskRequest);
        TaskDto taskDto = taskMapper.toDto(task);
        return ResponseEntity.ok(taskDto);  
    }

}
