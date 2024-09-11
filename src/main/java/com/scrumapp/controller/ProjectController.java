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
    @GetMapping
    public Optional<Project> getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }

    @PutMapping(path = "/api/project/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }

    @DeleteMapping(path = "/api/project/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable int id){
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()){
            projectService.deleteProjectById(id);
            return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Project with id"+ id + "was not found.", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping
    public void deleteAllProject(){
        projectService.deleteAllProject();
    }
}
