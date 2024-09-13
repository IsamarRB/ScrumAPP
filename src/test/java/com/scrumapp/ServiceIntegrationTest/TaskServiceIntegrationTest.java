package com.scrumapp.ServiceIntegrationTest;

import com.scrumapp.Repositories.ITaskRepository;
import com.scrumapp.model.Task;
import com.scrumapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskServiceIntegrationTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ITaskRepository taskRepository;

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba

        task1 = new Task();
        task1.setName("Task 1");
        task1.setDescription("Description Task 1");

        task2 = new Task();
        task2.setName("Task 2");
        task2.setDescription("Description Task 2");

        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
        assertEquals("Task 2", tasks.get(1).getName());
    }

    @Test
    public void testGetTaskById() {
        Task foundTask = taskService.getTaskById(task1.getId());

        assertNotNull(foundTask);
        assertEquals("Task 1", foundTask.getName());
    }

    @Test
    public void testCreateTask() {
        Task newTask = new Task();
        newTask.setName("New Task");
        newTask.setDescription("New Task Description");

        Task createdTask = taskService.createTask(newTask);

        assertNotNull(createdTask);
        assertEquals("New Task", createdTask.getName());

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(3, tasks.size()); // Ahora debería haber 3 tareas en la base de datos
    }

    @Test
    public void testUpdateTask() {
        task1.setName("Updated Task 1");
        Task updatedTask = taskService.updateTask(task1);

        assertNotNull(updatedTask);
        assertEquals("Updated Task 1", updatedTask.getName());

        Optional<Task> foundTask = taskRepository.findById(task1.getId());
        assertTrue(foundTask.isPresent());
        assertEquals("Updated Task 1", foundTask.get().getName());
    }

    @Test
    public void testDeleteTask() {
        taskService.deleteTask(task1.getId());

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size()); // Solo debe quedar una tarea
    }
}