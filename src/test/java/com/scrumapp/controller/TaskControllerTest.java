package com.scrumapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
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

    /*@Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
        task1 = new Task();
        task2 = new Task();
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(task1, task2);
        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetTaskById() {
        when(taskService.getTaskById(1)).thenReturn(task1);

        ResponseEntity<Task> response = taskController.getTaskById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task1, response.getBody());
    }

    @Test
    public void testGetTaskById_NotFound() {
        when(taskService.getTaskById(1)).thenReturn(null);

        ResponseEntity<Task> response = taskController.getTaskById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateTask() {
        when(taskService.createTask(task1)).thenReturn(task1);

        ResponseEntity<Task> response = taskController.createTask(task1);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task1, response.getBody());
    }

    @Test
    public void testUpdateTask() {
        when(taskService.updateTask(task1)).thenReturn(task1);

        ResponseEntity<Task> response = taskController.updateTask(1, task1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task1, response.getBody());
    }

    @Test
    public void testDeleteTask() {
        ResponseEntity<Void> response = taskController.deleteTask(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}*/