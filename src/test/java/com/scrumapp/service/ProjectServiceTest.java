package com.scrumapp.service;

import com.scrumapp.model.Project;
import com.scrumapp.repository.IProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {
    @Mock
    private IProjectRepository iProjectRepository;

    @InjectMocks
    private ProjectService projectService;

     private Project project1;
     private Project project2;

     @BeforeEach
    public void Setup(){
         MockitoAnnotations.openMocks(this);
         project1 = new Project();
         project1.setId(1);
         project1.setName("Animal Shelter");

         project2 = new Project();
         project2.setId(2);
         project2.setName("Veterinary Clinic");
     }

     @Test
    void createProject() throws  Exception{
         when(iProjectRepository.save(any(Project.class))).thenReturn(project2);
         Project newProject = projectService.createProject(project2);

     }

}
