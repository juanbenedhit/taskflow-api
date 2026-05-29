package com.example.taskflow.service;

import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.entity.Project;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.TaskStatus;
import com.example.taskflow.repository.ProjectRepository;
import com.example.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task createTask(Long projectId, TaskRequest request) {
        // 1. Cari project-nya berdasarkan ID
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project tidak ditemukan"));

        // 2. Buat task baru
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setStatus(TaskStatus.valueOf(request.getStatus().toUpperCase()));

        // 3. Hubungkan task ini dengan project yang ditemukan
        task.setProject(project);

        // 4. Simpan ke database
        return taskRepository.save(task);
    }
}