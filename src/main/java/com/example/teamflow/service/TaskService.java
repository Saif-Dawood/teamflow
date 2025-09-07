package com.example.teamflow.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.teamflow.model.Task;
import com.example.teamflow.repository.TaskRepository;

@Service
public class TaskService implements ITaskService {

	private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    public List<Task> getAll() {
        return this.repository.findAll();
    }

	public List<Task> getAll(String sortField, Optional<String> order) {
        if (sortField == "dueDate")  {
            if (order.toString() == "desc") {
                return this.repository.findAllByOrderByDueDateDesc();
            } else {
                return this.repository.findAllByOrderByDueDateAsc();
            }
        } else if (sortField == "Priority") {
            if (order.toString() == "desc") {
                return this.repository.findAllByOrderByPriorityDesc();
            } else {
                return this.repository.findAllByOrderByPriorityAsc();
            }
        } else {
            return null;
        }
    }
	
	public Optional<Task> findById(int id) {
        return this.repository.findById(id);
    }
	
	public Task save(Task task) {
        if (task == null) {
            return null;
        }
        return this.repository.save(task);
    }
	
	public void deleteById(int id){
        this.repository.deleteById(id);
    }
}
