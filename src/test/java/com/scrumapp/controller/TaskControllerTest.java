package com.scrumapp.controller;

import com.scrumapp.model.Task;
import com.scrumapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class TaskControllerTest {
    @Mock
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
}