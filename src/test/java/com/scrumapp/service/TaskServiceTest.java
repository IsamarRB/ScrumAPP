package com.scrumapp.service;

import com.scrumapp.repository.ITaskRepository;
import com.scrumapp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TaskServiceTest {
    @Mock
    private ITaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        task1 = new Task();
        task1.setId(1);
        task1.setName("Task 1");
        task1.setDescription("Description 1");

        task2 = new Task();
        task2.setId(2);
        task2.setName("Task 2");
        task2.setDescription("Description 2");
    }

    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals("Task 2", tasks.get(1).getName());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTaskById() {

        when(taskRepository.findById(1)).thenReturn(Optional.of(task1));

        Task foundTask = taskService.getTaskById(1);

        assertNotNull(foundTask);
        assertEquals("Task 1", foundTask.getName());

        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testGetTaskById_NotFound() {

        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        Task foundTask = taskService.getTaskById(1);

        assertNull(foundTask);

        verify(taskRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateTask() {

        when(taskRepository.save(task1)).thenReturn(task1);

        Task createdTask = taskService.createTask(task1);

        assertNotNull(createdTask);
        assertEquals("Task 1", createdTask.getName());

        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    public void testUpdateTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        Task updatedTask = taskService.updateTask(task1, 1);

        assertNotNull(updatedTask);
        assertEquals("Task 1", updatedTask.getName());

        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    public void testDeleteTask() {
        taskService.deleteTask(1);

        verify(taskRepository, times(1)).deleteById(1);
    }
}
