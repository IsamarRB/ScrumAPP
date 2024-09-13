package com.scrumapp.Repositories;

import com.scrumapp.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITaskRepository extends CrudRepository <Task, Integer> {
    Optional<Task> findById(Integer id);
}
