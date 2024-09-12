package com.scrumapp.controller;

import com.scrumapp.model.Project;
import com.scrumapp.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")

public class ProjectController {

    private ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }

    @GetMapping
    public List<Project> getAllProject(){
        return projectService.getAllProject();
    }

    @GetMapping(path = "/project/{id}")
    public Optional<Project> getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }

    @PutMapping (path = "/project/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }

    @DeleteMapping(path = "/project/{id}")
    public String deleteProjectById(@PathVariable int id){
        boolean ok = projectService.deleteProjectById(id);
        if (ok){
            return "Project with id" + id + "was deleted";
        } else{
            return "Project with id"+ id + "not found.";
        }
    }
}
