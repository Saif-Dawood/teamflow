package com.example.teamflow.model;

import java.time.LocalDateTime;

import com.example.teamflow.enums.TaskStatus;
import com.example.teamflow.enums.Priority;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
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

    public Task(
        String title,
        String description,
        TaskStatus status,
        LocalDateTime dueDate,
        Priority priority
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
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
}
