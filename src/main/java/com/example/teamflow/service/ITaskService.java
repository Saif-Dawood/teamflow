package com.example.teamflow.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.teamflow.model.Task;

public interface ITaskService {

    Page<Task> getAll();

	Page<Task> getAll(String sortField, Optional<String> order);
	
	Optional<Task> findById(int theId);
	
	Task save(Task theTask);
	
	void deleteById(int theId);
	
}
