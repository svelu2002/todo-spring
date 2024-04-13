package com.jpmc.todo.service;

import com.jpmc.todo.dto.StepDTO;
import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.exception.TaskNotFoundException;
import com.jpmc.todo.model.StepEntity;
import com.jpmc.todo.model.TaskEntity;
import com.jpmc.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testGetAllTasks() {
        // Given
        List<TaskDTO> tasksDTOS = new ArrayList<>();
        TaskDTO taskDTO1 = getTaskDTO1();
        tasksDTOS.add(taskDTO1);

        List<TaskEntity> taskEntities = new ArrayList<>();
        TaskEntity taskEntity1 = getTaskEntity1();
        taskEntities.add(taskEntity1);

        // When
        when(taskRepository.findAll()).thenReturn(taskEntities);

        // Then
        assertEquals(tasksDTOS, taskService.getAllTasks());

        // check if method is called only once
        verify(taskRepository, times(1)).findAll();

    }

    private TaskDTO getTaskDTO1() {
        List<StepDTO> task1Steps = new ArrayList<>();
        TaskDTO taskDTO1 = new TaskDTO(1, "Attend Orientation program", "Ask all sorts questions that you have", "New", Optional.of(task1Steps));
        StepDTO task1Step1 = new StepDTO(1, 1, "Be there on time");
        StepDTO task1Step2 = new StepDTO(2, 2, "Make sure you have pen and a note book");
        task1Steps.add(task1Step1);
        task1Steps.add(task1Step2);

        return taskDTO1;
    }

    private TaskEntity getTaskEntity1() {
        List<StepEntity> task1Steps = new ArrayList<>();
        TaskEntity taskEntity1 = new TaskEntity(1, "Attend Orientation program", "Ask all sorts questions that you have", "New", task1Steps);
        StepEntity task1Step1 = new StepEntity(1, 1, "Be there on time", taskEntity1);
        StepEntity task1Step2 = new StepEntity(2, 2, "Make sure you have pen and a note book", taskEntity1);
        task1Steps.add(task1Step1);
        task1Steps.add(task1Step2);

        return taskEntity1;
    }

    @Test
    public void testGetTaskById() {
        // Given
        TaskDTO taskDTO1 = getTaskDTO1();
        TaskEntity taskEntity1 = getTaskEntity1();

        // When
        when(taskRepository.findById(taskDTO1.id())).thenReturn(Optional.of(taskEntity1));

        // Then
        try {
            assertEquals(taskDTO1, taskService.getTaskById(taskEntity1.getId()));
        } catch (TaskNotFoundException e) {
            fail("TaskNotFoundException was thrown: " + e.getMessage());
        }


        // check if method is called only once
        verify(taskRepository, times(1)).findById(taskDTO1.id());
    }

    @Test
    public void testGetTaskByIdWithInvalidId() {
        // Given
        TaskDTO taskDTO1 = getTaskDTO1();
        TaskEntity taskEntity1 = getTaskEntity1();

        // When
        when(taskRepository.findById(taskDTO1.id())).thenReturn(Optional.empty());

        // Then
        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(taskEntity1.getId());
        });

        // check if method is called only once
        verify(taskRepository, times(1)).findById(taskDTO1.id());
    }
}
