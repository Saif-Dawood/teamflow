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
import com.example.teamflow.exception.InvalidPageNumberException;
import com.example.teamflow.exception.InvalidSortFieldException;
import com.example.teamflow.exception.InvalidTaskException;
import com.example.teamflow.exception.TaskNotFoundException;

@Service
public class TaskService implements ITaskService {

	private TaskRepository repository;
    private Pageable pageable = PageRequest.of(0, 5);

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    
	public Page<Task> getAll(String sortField, Optional<String> order, int pageNumber) {
        this.pageable = PageRequest.of(pageNumber, 5);

        Page<Task> tasksPage = this.repository.findAll(this.pageable);
        if (pageNumber >= tasksPage.getTotalPages()) {
            throw new InvalidPageNumberException("Page number is greater than number of pages");
        }

        if (sortField == null) {
            return tasksPage;
        }

        if ("duedate".compareTo(sortField.toLowerCase()) == 0)  {
            if ("desc".compareTo(order.get().toLowerCase()) == 0) {
                return this.repository.findAllByOrderByDueDateDesc(this.pageable);
            } else {
                return this.repository.findAllByOrderByDueDateAsc(this.pageable);
            }
        } else if ("priority".compareTo(sortField.toLowerCase()) == 0) {
            if ("desc".compareTo(order.get().toLowerCase()) == 0) {
                return this.repository.findAllByOrderByPriorityDesc(this.pageable);
            } else {
                return this.repository.findAllByOrderByPriorityAsc(this.pageable);
            }
        } else {
            throw new InvalidSortFieldException("The field name you provided is invalid or can't be sorted with");
        }
    }

    public Page<Task> findByTitle(String title, int pageNumber) {
        Page<Task> tasksPage = this.repository.findAllByTitleIgnoreCaseContaining(title, this.pageable);

        if (tasksPage.getTotalPages() == 0) {
            throw new TaskNotFoundException("Couldn't find any tasks with a title like: " + title);
        }

        if (pageNumber >= tasksPage.getTotalPages()) {
            throw new InvalidPageNumberException("Page number is greater than number of pages");
        }

        return tasksPage;
    }

    public Page<Task> findByStatus(TaskStatus status, int pageNumber) {
        Page<Task> tasksPage = this.repository.findAllByStatus(status, this.pageable);

        if (tasksPage.getTotalPages() == 0) {
            throw new TaskNotFoundException("Couldn't find any tasks with status: " + status);
        }

        if (pageNumber >= tasksPage.getTotalPages()) {
            throw new InvalidPageNumberException("Page number is greater than number of pages");
        }

        return tasksPage;
    }
	
	public Task findById(int id) {
        Optional<Task> task = this.repository.findById(id);

        if (task.isEmpty()) {
            throw new TaskNotFoundException("Couldn't find task with id: " + id);
        }

        return task.get();
    }
	
	public Task save(Task task) {
        if (task.getTitle() == null) {
            throw new InvalidTaskException("Title can't be empty");
        } else if (task.getDescription() == null) {
            throw new InvalidTaskException("Description can't be empty");
        }
        return this.repository.save(task);
    }
	
	public void deleteById(int id){
        Optional<Task> task = this.repository.findById(id);

        if (task == null) {
            throw new TaskNotFoundException("Couldn't find task with id: " + id);
        }

        this.repository.deleteById(id);
    }
}
