package com.scrumapp.service;

import com.scrumapp.model.Project;
import com.scrumapp.repository.IProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

         assertNotNull(newProject);
         assertNotNull(2, String.valueOf(newProject.getId()));
         assertNotNull("Animal Shelter", newProject.getName());

         verify(iProjectRepository, times(1)).save(project2);
     }

     @Test
     void getAllProjectTest(){
         List<Project> projectList = new ArrayList<>();
         projectList.add(project1);
         projectList.add(project2);

         when(iProjectRepository.findAll()).thenReturn(projectList);

         List<Project> allProject = projectService.getAllProject();

         assertNotNull(allProject);
         assertEquals(2, allProject.size());
         assertTrue(allProject.contains(project1));
         assertTrue(allProject.contains(project2));

         verify(iProjectRepository, times(1)).findAll();
     }

     @Test
    void getProjectByIdTest(){
         when(iProjectRepository.findById(1)).thenReturn(Optional.of(project1));

         Optional<Project> foundProject = projectService.getProjectById(1);

         assertTrue(foundProject.isPresent());
         assertEquals(project1.getId(), foundProject.get().getId());
         assertEquals(project1.getName(), foundProject.get().getName());
         verify(iProjectRepository, times(1)).findById(1);
     }
     @Test
     void updateProjectTest() {
         Project project = new Project();
         when(iProjectRepository.findById(1)).thenReturn(Optional.of(project));

         project.setName("Animal Shelters");

         Project updatedProject = projectService.updateProject(1, project);

         verify(iProjectRepository).save(project);
}
    @Test
    void deleteProjectIdTest(){
         int id = 2;
         projectService.deleteProjectById(id);
         verify(iProjectRepository).deleteById(id);
    }





}
