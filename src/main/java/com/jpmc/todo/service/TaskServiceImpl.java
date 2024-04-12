package com.jpmc.todo.service;

import com.jpmc.todo.dto.StepDTO;
import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.exception.StepNotFoundException;
import com.jpmc.todo.exception.TaskAlreadyExistsException;
import com.jpmc.todo.exception.TaskNotFoundException;
import com.jpmc.todo.model.StepEntity;
import com.jpmc.todo.model.TaskEntity;
import com.jpmc.todo.repository.StepRepository;
import com.jpmc.todo.repository.TaskRepository;
import com.jpmc.todo.util.EntityDTOConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StepRepository stepRepository;

    @Override
    // @Transactional
    public TaskDTO saveTask(TaskDTO taskDTO) throws TaskAlreadyExistsException {
        log.debug("Inside TaskServiceImpl.saveTask()");
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskDTO.id());
        if (optionalTaskEntity.isPresent()) {
            throw new TaskAlreadyExistsException("Task with the id " + taskDTO.id() + " already exists.");
        } else {
            // Save the task without any steps so that Task id can be assigned to the steps
            TaskEntity taskEntity = (TaskEntity) EntityDTOConverter.convertToEntity(taskDTO);
            taskEntity.setSteps(null);
            TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
            log.info("Task saved successfully");

            // Save the entities with the Task information
            if (taskDTO.steps().isPresent()) {
                List<StepDTO> stepDTOS= taskDTO.steps().get();
                if (!stepDTOS.isEmpty()) {
                    List<StepEntity> savedStepEntities = new ArrayList<>();
                    StepEntity stepEntity;
                    for (StepDTO stepDTO : stepDTOS) {
                        stepEntity = stepRepository.save((StepEntity) EntityDTOConverter.convertToEntity(stepDTO));
                        stepEntity.setTask(savedTaskEntity);
                        savedStepEntities.add(stepEntity);
                    }
                    savedTaskEntity.setSteps(savedStepEntities);

                    // Update the task with saved steps
                    savedTaskEntity = taskRepository.save(savedTaskEntity);
                } else {
                    log.info("Steps is empty in TaskDTO");
                }
            } else {
                log.info("Steps is not present in TaskDTO");
            }
            return (TaskDTO) EntityDTOConverter.convertToDTO(savedTaskEntity);
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

    @Override
    public TaskDTO addStepToTask(int id, StepDTO stepDTO) throws TaskNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
        List<StepEntity> steps;
        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();
            if (taskEntity.getSteps() != null) {
                steps = taskEntity.getSteps();
            } else {
                steps = new ArrayList<>();
            }

            // Set the task for the new step and save it
            StepEntity stepEntity = (StepEntity) EntityDTOConverter.convertToEntity(stepDTO);
            stepEntity.setTask(taskEntity);
            StepEntity savedStepEntity = stepRepository.save(stepEntity);

            // Add the saved Step to the Task's steps list and update the Task
            steps.add(savedStepEntity);
            taskEntity.setSteps(steps);
            taskEntity = taskRepository.save(taskEntity);
            return (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity);
        } else
            throw new TaskNotFoundException("Task with id " + id + " not found");
    }

    @Override
    public TaskDTO deleteStepFromTask(int taskId, int stepId) throws TaskNotFoundException, StepNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
        List<StepEntity> steps;
        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();
            if (taskEntity.getSteps() != null) {
                steps = taskEntity.getSteps();
                Optional<StepEntity> optionalStepEntity = stepRepository.findById(stepId);
                if (optionalStepEntity.isPresent()) {
                    steps.remove(optionalStepEntity.get());
                    taskEntity = taskRepository.save(taskEntity);
                    return (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity);
                } else {
                    throw new StepNotFoundException("Step with id " + stepId + " not found");
                }
            } else {
                throw new StepNotFoundException("Step with id " + stepId + " not found");
            }

        } else
            throw new TaskNotFoundException("Task with id " + taskId + " not found");
    }

    @Override
    public TaskDTO updateStepForTask(int taskId, int stepId, StepDTO stepDTO) throws TaskNotFoundException, StepNotFoundException {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
        List<StepEntity> steps;
        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();
            if (taskEntity.getSteps() != null) {
                steps = taskEntity.getSteps();
                Optional<StepEntity> optionalStepEntity = stepRepository.findById(stepId);
                if (optionalStepEntity.isPresent()) {
                    StepEntity stepEntity = optionalStepEntity.get();
                    int index = steps.indexOf(stepEntity);
                    if (index != -1) {
                        stepEntity.setInstruction(stepDTO.instruction());
                        steps.set(index, stepEntity);
                    }
                    // stepEntity = stepRepository.save(stepEntity);
                    taskEntity = taskRepository.save(taskEntity);
                    return (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity);
                } else {
                    throw new StepNotFoundException("Step with id " + stepId + " not found");
                }
            } else {
                throw new StepNotFoundException("Step with id " + stepId + " not found");
            }

        } else
            throw new TaskNotFoundException("Task with id " + taskId + " not found");
    }

    @Override
    public List<TaskDTO> getTasksByStatus(String status) {
        return taskRepository.getTasksByStatus(status).stream().map(taskEntity -> (TaskDTO) EntityDTOConverter.convertToDTO(taskEntity)).toList();
    }


}
