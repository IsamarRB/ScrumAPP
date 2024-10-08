package com.scrumapp.service;

import com.scrumapp.model.Project;
import com.scrumapp.repository.IProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectService {
    private final IProjectRepository iProjectRepository;

    public ProjectService(IProjectRepository iProjectRepository) {
        this.iProjectRepository = iProjectRepository;
    }

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
        Project existingProject = iProjectRepository.findById(id).orElse(null);

        if (existingProject != null) {
            existingProject.setName(project.getName());
            return iProjectRepository.save(existingProject);
        }
        return null;
    }

    public boolean deleteProjectById(int id) {
        try{
            iProjectRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
