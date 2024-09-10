package com.scrumapp.service;

import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TaskServiceTest {
    @Mock
    private ITaskRepository iTaskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetAllTasks() {
        // Given
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(iTaskRepository.findAll()).thenReturn(tasks);

        // When
        List<Task> result = taskService.getAllTasks();

        // Then
        assertEquals(tasks, result);
        verify(iTaskRepository).findAll();
    }

    @Test
    void testGetTaskById() {
        // Given
        Task task = new Task();
        when(iTaskRepository.findById("1")).thenReturn(Optional.of(task));

        // When
        Task result = taskService.getTaskById(1);

        // Then
        assertEquals(task, result);
        verify(iTaskRepository).findById("1");
    }

    @Test
    void testGetTaskById_NotFound() {
        // Given
        when(iTaskRepository.findById("1")).thenReturn(Optional.empty());

        // When
        Task result = taskService.getTaskById(1);

        // Then
        assertNull(result);
        verify(iTaskRepository).findById("1");
    }

    @Test
    void testCreateTask() {
        // Given
        Task task = new Task();
        when(iTaskRepository.save(task)).thenReturn(task);

        // When
        Task result = taskService.createTask(task);

        // Then
        assertEquals(task, result);
        verify(iTaskRepository).save(task);
    }

    @Test
    void testUpdateTask() {
        // Given
        Task task = new Task();
        when(iTaskRepository.save(task)).thenReturn(task);

        // When
        Task result = taskService.updateTask(task);

        // Then
        assertEquals(task, result);
        verify(iTaskRepository).save(task);
    }

    @Test
    void testDeleteTask() {
        // Given
        int id = 1;

        // When
        taskService.deleteTask(id);

        // Then
        verify(iTaskRepository).deleteById(String.valueOf(id));
    }
}
