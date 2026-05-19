package com.spring_app.sandbox.service;

import java.util.List;
import java.util.UUID;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.entity.Task;

public interface TaskService {
    
    // could return a create task response object instead of the entity (better decoupling but more complex), but for simplicity we will return the entity here
    Task createTask(CreateTaskRequest request);
    
    List<Task> listTasks();

    Task updateTask(UUID taskID, UpdateTaskRequest request);
}
