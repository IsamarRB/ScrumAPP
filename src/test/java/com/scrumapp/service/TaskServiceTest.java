package com.scrumapp.service;

import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)

@SpringBootTest
@Transactional
@Rollback
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ITaskRepository taskRepository;

    @BeforeEach
    public void setup() {

        taskRepository.deleteAll();
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task();
        newTask.setName("Nueva Tarea");
        newTask.setDescription("Descripción de la tarea");

        Task createdTask = taskService.createTask(newTask);

        assertNotNull(createdTask.getId());
        assertEquals("Nueva Tarea", createdTask.getName());
        assertEquals("Descripción de la tarea", createdTask.getDescription());
    }

    @Test
    public void testGetAllTasks() {

        Task task1 = new Task();
        task1.setName("Tarea 1");
        task1.setDescription("Descripción 1");
        taskService.createTask(task1);

        Task task2 = new Task();
        task2.setName("Tarea 2");
        task2.setDescription("Descripción 2");
        taskService.createTask(task2);

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task();
        task.setName("Tarea por ID");
        task.setDescription("Descripción de la tarea");
        Task savedTask = taskService.createTask(task);

        Task foundTask = taskService.getTaskById(savedTask.getId());

        assertNotNull(foundTask);
        assertEquals("Tarea por ID", foundTask.getName());
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        task.setName("Tarea original");
        task.setDescription("Descripción original");
        Task savedTask = taskService.createTask(task);

        savedTask.setName("Tarea actualizada");
        savedTask.setDescription("Descripción actualizada");
        Task updatedTask = taskService.updateTask(savedTask);

        assertEquals("Tarea actualizada", updatedTask.getName());
        assertEquals("Descripción actualizada", updatedTask.getDescription());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setName("Tarea a eliminar");
        task.setDescription("Descripción de la tarea");
        Task savedTask = taskService.createTask(task);

        taskService.deleteTask(savedTask.getId());

        Optional<Task> deletedTask = taskRepository.findById(savedTask.getId());
        assertTrue(deletedTask.isEmpty());
    }
}
    /*@Mock
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
        when(iTaskRepository.findById(Integer.parseInt("1"))).thenReturn(Optional.of(task));

        // When
        Task result = taskService.getTaskById(1);

        // Then
        assertEquals(task, result);
        verify(iTaskRepository).findById(Integer.parseInt("1"));
    }

    @Test
    void testGetTaskById_NotFound() {
        // Given
        when(iTaskRepository.findById(Integer.parseInt("1"))).thenReturn(Optional.empty());

        // When
        Task result = taskService.getTaskById(1);

        // Then
        assertNull(result);
        verify(iTaskRepository).findById(Integer.parseInt("1"));
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
        verify(iTaskRepository).deleteById(Integer.valueOf(String.valueOf(id)));
    }
}*/
