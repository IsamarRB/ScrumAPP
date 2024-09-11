//package com.scrumapp.controller;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.scrumapp.ScrumAppApplication;
//import com.scrumapp.model.Project;
//import com.scrumapp.service.ProjectService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@ContextConfiguration(classes = ScrumAppApplication.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProjectControllerTest {
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    private ProjectService projectService;
//
//    private Project project;
//
//    @BeforeEach
//    void setUp() {
//        project = new Project();
//    }
//
//    @Test
//    void createProjectTest() throws Exception {
//        when(projectService.createProject(any(Project.class))).thenReturn(project);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/project")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(project)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Project"));
//    }
//
//    @Test
//    void getAllProjectsTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/project"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void getProjectByIdTest() throws Exception {
//        when(projectService.getProjectById(anyInt())).thenReturn(Optional.of(project));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/project/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Project"));
//    }
//
//    @Test
//    void updateProjectTest() throws Exception {
//        when(projectService.updateProject(anyInt(), any(Project.class))).thenReturn(project);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/project/{id}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(project)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Project"));
//    }
//
//    @Test
//    void deleteProjectByIdTest() throws Exception {
//        when(projectService.getProjectById(anyInt())).thenReturn(Optional.of(project));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/project/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Project deleted successfully"));
//    }
//
//    @Test
//    void deleteAllProjectsTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/project"))
//                .andExpect(status().isOk());
//    }
//}






