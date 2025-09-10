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

    public Task() {
    }

    public void update(Task task) {

        if (task.getTitle() != null) {
            this.title = task.title;
        } if (task.getDescription() != null) {
            this.description = task.description;
        } if (task.getStatus() != null) {
            this.status = task.status;
        } if (task.getDueDate() != null) {
            this.dueDate = task.dueDate;
        } if (task.getPriority() != null) {
            this.priority = task.priority;
        }

    }

    @Override
    public String toString() {
        return "title: " + this.title +
            ", description: " + this.description +
            ", status: " + this.status +
            ", dueDate: " + this.dueDate +
            ", priority: " + this.priority;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}
