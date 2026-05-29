package com.example.taskflow.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank(message = "Judul task tidak boleh kosong")
    private String title;

    @NotBlank(message = "Status task tidak boleh kosong")
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}