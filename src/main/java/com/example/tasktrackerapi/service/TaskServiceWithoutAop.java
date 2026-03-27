package com.example.tasktrackerapi.service;

import com.example.tasktrackerapi.entity.Task;
import com.example.tasktrackerapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ВЕРСИЯ БЕЗ AOP
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TaskServiceWithoutAop {
    private final TaskRepository taskRepository;

    /**
     * Найти все задачи
     */
    public List<Task> findAll() {
        // Логирование
        log.info("Finding all tasks");

        // Замер производительности
        long start = System.currentTimeMillis();

        try {
            // Бизнес-логика - 1 строчка!
            List<Task> tasks = taskRepository.findAll();

            log.info("Found {} tasks", tasks.size());

            return tasks;
        } catch (Exception e) {
            // Обработка ошибок
            log.error("Error finding tasks");
            throw e;
        } finally {
            // Замер производительности
            long duration = System.currentTimeMillis() - start;
            log.info("Method findAll took {}ms", duration);
        }
    }

    /**
     * Найти задачу по ID
     */
    public Task findById(Long id) {
        log.info("Finding task by id: {}", id);
        long start = System.currentTimeMillis();

        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found: " + id));

            log.info("Fond task: {}", task.getTitle());
            return task;
        } catch (Exception e) {
            // Обработка ошибок
            log.error("Error finding task", e);
            throw e;
        } finally {
            // Замер производительности
            long duration = System.currentTimeMillis() - start;
            log.info("Method findById took {}ms", duration);
        }
    }

    /**
     * Создать задачу
     */
    @Transactional
    public Task create(Task task) {
        log.info("Creating task: {}", task.getTitle());
        long start = System.currentTimeMillis();

        try {
            // Валидация
            if (task.getTitle() == null || task.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }

            Task created = taskRepository.save(task);
            log.info("Task created with id: {}", created.getId());

            return created;
        } catch (Exception e) {
            // Обработка ошибок
            log.error("Error creating task", e);
            throw e;
        } finally {
            // Замер производительности
            long duration = System.currentTimeMillis() - start;
            log.info("Method create took {}ms", duration);
        }
    }
}









