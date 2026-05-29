package com.example.taskflow.dto;

import jakarta.validation.constraints.NotBlank;

public class ProjectRequest {

    // @NotBlank memastikan data ini tidak boleh dikosongkan oleh user
    @NotBlank(message = "Nama project tidak boleh kosong")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}