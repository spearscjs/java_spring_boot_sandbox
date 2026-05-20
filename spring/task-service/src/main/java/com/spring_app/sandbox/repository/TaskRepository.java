package com.spring_app.sandbox.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring_app.sandbox.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    
}
