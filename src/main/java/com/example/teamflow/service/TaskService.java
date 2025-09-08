package com.example.teamflow.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.teamflow.model.Task;
import com.example.teamflow.repository.TaskRepository;
import com.example.teamflow.enums.TaskStatus;

@Service
public class TaskService implements ITaskService {

	private TaskRepository repository;
    private Pageable pageable = PageRequest.of(0, 5);

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
    public Page<Task> getAll() {
        return this.repository.findAll(this.pageable);
    }

	public Page<Task> getAll(String sortField, Optional<String> order) {
        if (sortField == "dueDate")  {
            if (order.toString() == "desc") {
                return this.repository.findAllByOrderByDueDateDesc(this.pageable);
            } else {
                return this.repository.findAllByOrderByDueDateAsc(this.pageable);
            }
        } else if (sortField == "Priority") {
            if (order.toString() == "desc") {
                return this.repository.findAllByOrderByPriorityDesc(this.pageable);
            } else {
                return this.repository.findAllByOrderByPriorityAsc(this.pageable);
            }
        } else {
            return null;
        }
    }

    public Page<Task> findByTitle(String title) {
        return this.repository.findAllByTitleIgnoreCaseContaining(title, this.pageable);
    }

    public Page<Task> findByStatus(TaskStatus status) {
        return this.repository.findAllByStatus(status, this.pageable);
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
