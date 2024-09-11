package com.scrumapp.service;

import com.scrumapp.model.Project;
import com.scrumapp.repository.IProjectRepository;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/project")

public class ProjectService {
    private IProjectRepository iProjectRepository;

    public Project createProject(Project project) {
        return iProjectRepository.save(project);
    }

    public List<Project> getAllProject() {
        return iProjectRepository.findAll();
    }

    public Optional<Project> getProjectById(int id) {
        return iProjectRepository.findById(id);
    }

    public Project updateProject(int id, Project project) {
        return iProjectRepository.findById(id).orElse(null);
    }

    public void deleteProjectById(int id) {
        iProjectRepository.deleteById(id);
    }

    public  void  deleteAllProject() {
        iProjectRepository.deleteAll();
    }
}
