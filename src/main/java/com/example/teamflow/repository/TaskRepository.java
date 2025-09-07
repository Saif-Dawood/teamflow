package com.example.teamflow.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teamflow.model.Task;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    public List<Task> findAllByOrderByDueDateAsc();

    public List<Task> findAllByOrderByDueDateDesc();

    public List<Task> findAllByOrderByPriorityAsc();

    public List<Task> findAllByOrderByPriorityDesc();

}
