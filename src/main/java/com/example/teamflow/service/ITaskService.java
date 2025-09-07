package com.example.teamflow.service;

import java.util.List;
import java.util.Optional;

import com.example.teamflow.model.Task;

public interface ITaskService {

    List<Task> getAll();

	List<Task> getAll(String sortField, Optional<String> order);
	
	Optional<Task> findById(int theId);
	
	Task save(Task theTask);
	
	void deleteById(int theId);
	
}
