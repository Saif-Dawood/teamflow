package com.example.teamflow.model;

import java.time.LocalDateTime;

import com.example.teamflow.enums.TaskStatus;
import com.example.teamflow.enums.Priority;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Task() {
    }

    public Long getId() {
		return id;
	}

    public void update(Task task) {
        this.title = task.title;
        this.description = task.description;
        this.status = task.status;
        this.dueDate = task.dueDate;
        this.priority = task.priority;
    }

    @Override
    public String toString() {
        return "title: " + this.title +
            ", description: " + this.description +
            ", status: " + this.status +
            ", dueDate: " + this.dueDate +
            ", priority: " + this.priority;
    }
}
