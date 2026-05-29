package com.example.taskflow.service;

import com.example.taskflow.dto.ProjectRequest;
import com.example.taskflow.entity.Project;
import com.example.taskflow.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Dependency Injection: Menyambungkan Service dengan Repository
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Fungsi untuk membuat Project baru
    public Project createProject(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());

        // Simpan ke database dan kembalikan hasilnya
        return projectRepository.save(project);
    }

    // Fungsi untuk mengambil semua Project
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}