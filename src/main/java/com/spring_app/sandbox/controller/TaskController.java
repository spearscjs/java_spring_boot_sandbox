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

// injects dependencies via constructor injection, this is the recommended way to do it in Spring, it is more testable 
// and makes it clear what the dependencies are, also allows for immutability of the fields, which is a good practice
@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    
    // task service to interact with service layer
    private final TaskService taskService;
    // task mapper takes objects owned by presentation layer (dto) and converts them to objects owned by service layer (request objects) 
    // and vice versa, it is the responsibility of the controller to use the mapper to convert between these objects,
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    // TaskDto is the type returned in the response body.
    @PostMapping // POST /api/v1/tasks
    // Example request body matching CreateTaskRequestDto:
    /*
    {
        "title": "My Task",
        "description": "This is a task",
        "dueDate": "2024-12-31",
        "priority": "HIGH"
    }
    */
    // The incoming `CreateTaskRequestDto` is validated automatically because of `@Valid` on the parameter.
    // If validation fails, Spring throws `MethodArgumentNotValidException` and (by default) returns HTTP 400 with error details.
    // On success, the controller maps the DTO to a `CreateTaskRequest`, calls the service to create the task,
    // maps the returned `Task` to `TaskDto`, and returns it in the response.
    // Note: this method currently returns HTTP 200 OK; it's conventional to return 201 Created for create operations
    // and include a Location header pointing to the new resource.
    public ResponseEntity<TaskDto> createTask(
        // `@Valid` triggers validation of the request body before the method executes.
        // If invalid, a 400 Bad Request is returned by default.
        // `@RequestBody` tells Spring to deserialize the JSON request body into a `CreateTaskRequestDto` object.
        // The controller method is responsible for converting the incoming DTO to a service layer request object,
        // calling the service, and then converting the service layer response back to a DTO for the API response.
        @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto
    ) {
            
            CreateTaskRequest createTaskRequest = taskMapper.fromDto(createTaskRequestDto);
            // Map `CreateTaskRequestDto` to a service-layer `CreateTaskRequest` before calling the service.
            // The service layer should not depend on web/presentation DTOs; it should accept
            // service request objects. This separation of concerns keeps transport/validation
            // logic in the controller and business logic in the service, preventing transport
            // details from leaking into the domain API and making the service API more stable.
            Task task = taskService.createTask(createTaskRequest);
            TaskDto taskDto = taskMapper.toDto(task);

            // repsonse entity is a wrapper around the response body, it allows us to set the status code and headers of the response,
            //  in this case we are just returning a 200 OK response with the taskDto as the body,
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
