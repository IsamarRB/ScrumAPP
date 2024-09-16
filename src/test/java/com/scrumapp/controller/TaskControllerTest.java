package com.scrumapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrumapp.model.Task;
import com.scrumapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;
    private MockMvc mockMvc;
    private Task task1;
    private Task task2;
    private List<Task> taskList;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        task1 = new Task();
        task1.setId(1);
        task1.setName("Test with Mockito");
        task1.setDescription("hola");
        task1.setStatus("finished");

        task2 = new Task();
        task2.setId(2);
        task2.setName("Create CRUD");
        task2.setDescription("hola2");
        task2.setStatus("in progress");

        taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

    }

    @Test
    public void GetAllTasksTest() throws Exception {

        when(taskService.getAllTasks()).thenReturn(taskList);

        mockMvc.perform(get("/api/task"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void GetTaskByIdTest() throws Exception {
        when(taskService.getTaskById(1)).thenReturn(task1);

        mockMvc.perform(get("/api/task/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskService.getTaskById(1)).thenReturn(null);

        ResponseEntity<Task> response = taskController.getTaskById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void CreateTaskTest() throws Exception{
        when(taskService.createTask(any(Task.class))).thenReturn(task2);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(task2);

        mockMvc.perform(post("/api/task/create")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Create CRUD"))
                .andExpect(jsonPath("$.description").value("hola2"))
                .andExpect(jsonPath("$.status").value("in progress"));
    }

    @Test
    public void UpdateTaskTest() throws Exception {
        //doNothing().when(taskService).updateTask(task1, 1);
        when(taskService.updateTask(task1, 1)).thenReturn(task1);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(task1);

        mockMvc.perform(put("/api/task/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTask() {
        ResponseEntity<Void> response = taskController.deleteTask(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}