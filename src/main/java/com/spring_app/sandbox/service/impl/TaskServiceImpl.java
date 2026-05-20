package com.spring_app.sandbox.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = false)
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
            now,
            "Task-Service-User"
        );
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(UUID taskID, UpdateTaskRequest request) {

        Task task = taskRepository.findById(taskID)
            .orElseThrow(() -> new TaskNotFoundException(taskID));
        
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setUpdated(Instant.now());

        // TODO: optimistic locking (@Version) for production-grade concurrency control
        // no need to explicitly call save() here as taskRepository.findById returns a managed entity, and changes will be flushed at transaction commit
        return task;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Direction.ASC, "created"));
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskId) {
        try {
            taskRepository.deleteById(taskId);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException(taskId);
        }
        
    }

}
