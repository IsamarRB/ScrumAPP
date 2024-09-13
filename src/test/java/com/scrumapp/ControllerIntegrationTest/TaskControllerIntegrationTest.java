package com.scrumapp.ControllerIntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll();

        task1 = new Task();
        task1.setName("Tarea 1");
        task1.setDescription("Descripción Tarea 1");

        task2 = new Task();
        task2.setName("Tarea 2");
        task2.setDescription("Descripción Tarea 2");

        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @Test
    public void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Tarea 1"))
                .andExpect(jsonPath("$[1].name").value("Tarea 2"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        mockMvc.perform(get("/tasks/{id}", task1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tarea 1"))
                .andExpect(jsonPath("$.description").value("Descripción Tarea 1"));
    }

    @Test
    public void testGetTaskById_NotFound() throws Exception {
        mockMvc.perform(get("/tasks/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTask() throws Exception {
        Task newTask = new Task();
        newTask.setName("Nueva Tarea");
        newTask.setDescription("Descripción Nueva Tarea");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nueva Tarea"))
                .andExpect(jsonPath("$.description").value("Descripción Nueva Tarea"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        task1.setName("Tarea Actualizada");
        task1.setDescription("Descripción Actualizada");

        mockMvc.perform(put("/tasks/{id}", task1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tarea Actualizada"))
                .andExpect(jsonPath("$.description").value("Descripción Actualizada"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/{id}", task1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


        MvcResult result = mockMvc.perform(get("/tasks/{id}", task1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(result.getResponse().getStatus(), 404);
    }
}
