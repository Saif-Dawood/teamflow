package com.example.teamflow.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teamflow.model.Task;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    public Page<Task> findAllByOrderByDueDateAsc(Pageable pageable);

    public Page<Task> findAllByOrderByDueDateDesc(Pageable pageable);

    public Page<Task> findAllByOrderByPriorityAsc(Pageable pageable);

    public Page<Task> findAllByOrderByPriorityDesc(Pageable pageable);

}
