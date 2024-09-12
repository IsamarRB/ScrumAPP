package com.scrumapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrumapp.model.Project;
import com.scrumapp.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;
    private MockMvc mockMvc;
    private Project project1;
    private Project project2;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();

        project1 = new Project();
        project1.setId(1);
        project1.setName("Animal Shelter");

        project2 = new Project();
        project2.setId(1);
        project2.setName("Veterinary Clinic");
    }

    @Test
    void  CreateProject() throws Exception{
        when(projectService.createProject(any(Project.class))).thenReturn(project1);

        ObjectMapper objectMapper = new ObjectMapper();
        String projectJson = objectMapper.writeValueAsString(project1);

        mockMvc.perform(post("/api/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Animal Shelter"));
    }

    @Test
    void  getProjectById() throws Exception{
        when(projectService.getProjectById(2)).thenReturn(Optional.of(project2));

        mockMvc.perform(get("/project/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Veterinary CLinic"));
    }
    @Test
    void UpdateProject() throws Exception{
        when(projectService.updateProject(2, project2));
        ObjectMapper objectMapper = new ObjectMapper();

        String projectJson = objectMapper.writeValueAsString(project2);

        mockMvc.perform(put("/project/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isOk());
    }

    @Test
    void DeleteProjectById() throws Exception{
        when(projectService.deleteProjectById(1)).thenReturn(true);

        mockMvc.perform(delete("/project/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Animal Shelter"));

    }
}






