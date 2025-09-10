package com.example.teamflow.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.teamflow.enums.TaskStatus;
import com.example.teamflow.model.Task;

public interface ITaskService {

	Page<Task> getAll(String sortField, Optional<String> order, int pageNumber);
	
	Task findById(int theId);

    Page<Task> findByTitle(String title, int pageNumber);

    Page<Task> findByStatus(TaskStatus status, int pageNumber);
	
	Task save(Task theTask);
	
	void deleteById(int theId);
	
}
