package com.scrumapp.repository;

import com.scrumapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
