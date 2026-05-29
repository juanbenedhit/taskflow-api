package com.example.taskflow.controller;

import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.entity.Task;
import com.example.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint: POST /api/projects/{projectId}/tasks
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskRequest request) {

        Task newTask = taskService.createTask(projectId, request);
        return ResponseEntity.ok(newTask);
    }
}