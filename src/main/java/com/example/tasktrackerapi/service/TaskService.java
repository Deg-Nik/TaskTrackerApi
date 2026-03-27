package com.example.tasktrackerapi.service;

import com.example.tasktrackerapi.annotation.Monitored;
import com.example.tasktrackerapi.annotation.Secured;
import com.example.tasktrackerapi.entity.Task;
import com.example.tasktrackerapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Найти все задачи
     */
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /**
     * Найти задачу по ID
     *
     * @Monitored - замер по производительности
     */
    @Monitored(value = "Find task by ID", threshold = 500)
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    /**
     * Создать задачу
     *
     * @Secured     - проверка прав
     * @Monitored   - замер времени
     */
    @Transactional
//    @Secured(role = "USER")
    @Monitored(value = "Create task", threshold = 1000)
    public Task create(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty())
            throw new IllegalArgumentException("Title is required");

        return taskRepository.save(task);
    }

    /**
     * Обновить задачу
     */
    @Transactional
//    @Secured(role = "USER")
    @Monitored(value = "Update task", threshold = 1000)
    public Task update(Long id, Task updatedTask) {
        Task task = findById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    /**
     * Удалить задачу
     *
     * Только ADMIN может удалять
     */
    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
