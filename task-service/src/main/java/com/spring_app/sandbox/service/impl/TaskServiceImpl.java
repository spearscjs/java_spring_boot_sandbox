package com.spring_app.sandbox.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring_app.sandbox.domain.CreateTaskRequest;
import com.spring_app.sandbox.domain.UpdateTaskRequest;
import com.spring_app.sandbox.domain.entity.Task;
import com.spring_app.sandbox.domain.entity.TaskStatus;
import com.spring_app.sandbox.exception.TaskNotFoundException;
import com.spring_app.sandbox.repository.TaskRepository;
import com.spring_app.sandbox.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();
        Task task = new Task(
            null,
            request.title(),
            request.description(),
            request.dueDate(),
            TaskStatus.OPEN,
            request.priority(),
            now,
            now
        );
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(UUID taskID, UpdateTaskRequest request) {

        Task task = taskRepository.findById(taskID)
            .orElseThrow(() -> new TaskNotFoundException(taskID));
        
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setUpdated(Instant.now());

        // TODO: Add @Transactional and optimistic locking (@Version) for production-grade concurrency control
        return taskRepository.save(task);
    }
    
    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Direction.ASC, "created"));
    }

}
