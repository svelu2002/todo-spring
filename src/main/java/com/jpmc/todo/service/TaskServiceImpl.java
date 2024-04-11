package com.jpmc.todo.service;

import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.exception.TaskAlreadyExistsException;
import com.jpmc.todo.exception.TaskNotFoundException;
import com.jpmc.todo.model.TaskEntity;
import com.jpmc.todo.repository.TaskRepository;
import com.jpmc.todo.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) throws TaskAlreadyExistsException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskDTO.id());
        if (optionalTaskEntity.isPresent()) {
            throw new TaskAlreadyExistsException("Task with the id " + taskDTO.id() + " already exists.");
        } else {
            TaskEntity taskEntity = taskRepository.save((TaskEntity) EntityDTOConverter.convertToEntity(taskDTO));
            return (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity);
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream()
                .map(taskEntity -> (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity))
                .toList();
    }

    @Override
    public TaskDTO updateTask(int id, TaskDTO taskDTO) throws TaskNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = taskRepository.save((TaskEntity) EntityDTOConverter.convertToEntity(taskDTO));
            return (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity);
        } else
            throw new TaskNotFoundException("Task with id " + id + " not found");
    }

    @Override
    public TaskDTO getTaskById(int id) throws TaskNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
        if (optionalTaskEntity.isPresent())
            return (TaskDTO) EntityDTOConverter.convertToDTO(optionalTaskEntity.get());
        else
            throw new TaskNotFoundException("Task with id " + id + " not found");
    }

    @Override
    public void deleteTask(int id) throws TaskNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
        if (optionalTaskEntity.isPresent())
            taskRepository.delete(optionalTaskEntity.get());
        else
            throw new TaskNotFoundException("Task with id " + id + " not found");
    }
}
