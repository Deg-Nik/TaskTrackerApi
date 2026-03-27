package com.example.tasktrackerapi.repository;

import com.example.tasktrackerapi.entity.Task;
import com.example.tasktrackerapi.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    long countByStatus(TaskStatus status);
}
