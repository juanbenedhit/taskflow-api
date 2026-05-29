package com.example.taskflow.service;

import com.example.taskflow.dto.TaskRequest;
import com.example.taskflow.entity.Project;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.TaskStatus;
import com.example.taskflow.repository.ProjectRepository;
import com.example.taskflow.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_Success() {
        // --- 1. ARRANGE (Persiapan Data Palsu) ---
        Long projectId = 1L;
        Project mockProject = new Project();
        mockProject.setId(projectId);
        mockProject.setName("Proyek Rahasia");

        TaskRequest request = new TaskRequest();
        request.setTitle("Setup Server");
        request.setStatus("TODO");

        Task mockSavedTask = new Task();
        mockSavedTask.setId(100L);
        mockSavedTask.setTitle("Setup Server");
        mockSavedTask.setStatus(TaskStatus.TODO);
        mockSavedTask.setProject(mockProject);

        // Ajari Mockito cara menjawab
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(mockProject));
        when(taskRepository.save(any(Task.class))).thenReturn(mockSavedTask);

        // --- 2. ACT (Eksekusi Fungsi) ---
        Task result = taskService.createTask(projectId, request);

        // --- 3. ASSERT (Validasi Hasil) ---
        assertNotNull(result);
        assertEquals("Setup Server", result.getTitle());
        assertEquals(projectId, result.getProject().getId());

        // Pastikan repository benar-benar dipanggil 1 kali
        verify(projectRepository, times(1)).findById(projectId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void createTask_ProjectNotFound_ThrowsException() {
        // --- 1. ARRANGE ---
        Long wrongProjectId = 99L; // ID yang tidak ada
        TaskRequest request = new TaskRequest();
        request.setTitle("Task Mustahil");

        // Ajari Mockito untuk mengembalikan data kosong (tidak ketemu)
        when(projectRepository.findById(wrongProjectId)).thenReturn(Optional.empty());

        // --- 2 & 3. ACT & ASSERT (Eksekusi dan Validasi Error) ---
        // Kita mengecek apakah fungsi tersebut benar-benar melemparkan RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.createTask(wrongProjectId, request);
        });

        // Pastikan pesan error-nya sesuai dengan yang kita tulis di TaskService
        assertEquals("Project tidak ditemukan", exception.getMessage());

        // Pastikan taskRepository.save TIDAK PERNAH dipanggil karena sudah error duluan
        verify(taskRepository, never()).save(any(Task.class));
    }
}
