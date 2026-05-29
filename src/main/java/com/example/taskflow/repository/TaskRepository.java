package com.example.taskflow.repository;

import com.example.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Fitur tambahan: Kita buat fungsi untuk mencari semua Task berdasarkan ID
    // Project-nya
    List<Task> findByProjectId(Long projectId);
}