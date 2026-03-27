package com.example.tasktrackerapi.controller;

/**
 * @author : Nikolai Degtiarev
 * created : 24.03.26
 *
 *
 **/
import com.example.tasktrackerapi.entity.Task;
import com.example.tasktrackerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    /**
     * Получить все задачи
     *
     * AOP автоматически:
     * - залогирует вызов;
     * - замерит время;
     * - обработает ошибки;
     * - закэширует результат.
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Получить задачу по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Создать задачу
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Обновить задачу
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        Task updated = taskService.update(id, task);
        return ResponseEntity.ok(updated);
    }

    /**
     * Удалить задачу
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

