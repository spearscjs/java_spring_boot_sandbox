package com.spring_app.sandbox.service;

import java.util.List;
import java.util.UUID;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.entity.Task;

public interface TaskService {
    
    Task createTask(CreateTaskRequest request);
    
    List<Task> listTasks();

    Task updateTask(UUID taskID, UpdateTaskRequest request);

    void deleteTask(UUID taskId);
    
}
