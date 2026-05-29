package com.example.taskflow.controller;

import com.example.taskflow.dto.ProjectRequest;
import com.example.taskflow.entity.Project;
import com.example.taskflow.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Endpoint POST: Untuk membuat project baru
    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest request) {
        Project newProject = projectService.createProject(request);
        return ResponseEntity.ok(newProject); // Mengembalikan status 200 OK beserta datanya
    }

    // Endpoint GET: Untuk melihat semua project
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}