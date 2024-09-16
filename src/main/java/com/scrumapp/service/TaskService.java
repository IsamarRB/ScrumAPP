package com.scrumapp.service;

import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
    }

    public List<Task> getAllTasks() {
        return (List<Task>) iTaskRepository.findAll();
    }

    public Task getTaskById(Integer id) {return iTaskRepository.findById((id)).orElse(null);}

    public Task createTask(Task task) {
        return iTaskRepository.save(task);
    }

    public Task updateTask(Task task, Integer id) {
        task.setId(id);
        return iTaskRepository.save(task);
    }

    public void deleteTask(Integer id) {iTaskRepository.deleteById((id));}
}