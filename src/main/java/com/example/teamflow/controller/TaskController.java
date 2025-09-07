package com.example.teamflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
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
    public List<Task> getTasks() {

        // get the tasks from db
        List<Task> tasks = taskService.getAll();

        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") int taskId) {
        Task task = this.taskService.findById(taskId).get();
        return task;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        System.out.println(task.toString());
        System.out.println("qwerty");
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
