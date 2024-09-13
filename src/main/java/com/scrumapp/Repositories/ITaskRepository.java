package com.scrumapp.Repositories;

import com.scrumapp.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITaskRepository extends CrudRepository <Task, Integer> {
    Optional<Task> findById(Integer id);
}
