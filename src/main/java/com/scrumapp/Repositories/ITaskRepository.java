package com.scrumapp.Repositories;

import com.scrumapp.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface ITaskRepository extends CrudRepository <Task, Integer> {
}
