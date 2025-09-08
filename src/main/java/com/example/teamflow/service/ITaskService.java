package com.example.teamflow.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.teamflow.enums.TaskStatus;
import com.example.teamflow.model.Task;

public interface ITaskService {

    Page<Task> getAll();

	Page<Task> getAll(String sortField, Optional<String> order);
	
	Optional<Task> findById(int theId);

    Page<Task> findByTitle(String title);

    Page<Task> findByStatus(TaskStatus status);
	
	Task save(Task theTask);
	
	void deleteById(int theId);
	
}
