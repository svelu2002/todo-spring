package com.jpmc.todo.service;

import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.exception.TaskAlreadyExistsException;
import com.jpmc.todo.exception.TaskNotFoundException;

import java.util.List;

public interface TaskService {

    public TaskDTO saveTask(TaskDTO taskDTO) throws TaskAlreadyExistsException;

    public List<TaskDTO> getAllTasks();

    public TaskDTO updateTask(int id, TaskDTO taskDTO) throws TaskNotFoundException;

    public TaskDTO getTaskById(int id) throws TaskNotFoundException;

    public void deleteTask(int id) throws TaskNotFoundException;

}
