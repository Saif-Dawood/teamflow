package com.example.teamflow.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.teamflow.enums.TaskStatus;
import com.example.teamflow.model.Task;
import com.example.teamflow.service.ITaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Page<Task> getTasks(
        @RequestParam(required = false) String sort,
        @RequestParam(required = false) String order,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String status
    ) {

        if (title != null) {
            return taskService.findByTitle(title);
        } else if (status != null) {
            return taskService.findByStatus(TaskStatus.valueOf(status));
        }


        Page<Task> tasks = null;
        if (sort != null){
            tasks = taskService.getAll(sort, Optional.ofNullable(order));
        }

        if (tasks == null) {
            tasks = taskService.getAll();
        }

        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") int taskId) {
        Task task = this.taskService.findById(taskId).get();
        return task;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        this.taskService.save(task);
        return task;
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable("id") int taskId, @RequestBody Task newTask) {
        Task task = this.taskService.findById(taskId).get();
        task.update(newTask);
        this.taskService.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") int taskId) {
        this.taskService.deleteById(taskId);
    }

}
