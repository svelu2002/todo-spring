package com.jpmc.todo.util;

import com.jpmc.todo.dto.BaseDTO;
import com.jpmc.todo.dto.StepDTO;
import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.model.BaseEntity;
import com.jpmc.todo.model.StepEntity;
import com.jpmc.todo.model.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EntityDTOConverter {
    public static BaseEntity convertToEntity(BaseDTO baseDTO) {
        if (baseDTO instanceof TaskDTO taskDTO) {
            List<StepEntity> stepEntities = null;
            Optional<List<StepDTO>> optionalStepDTOS = taskDTO.steps();
            if (optionalStepDTOS.isPresent()) {
                List<StepDTO> stepDTOS = optionalStepDTOS.get();
                if (!stepDTOS.isEmpty()) {
                    stepEntities = stepDTOS.stream().map(stepDTO -> (StepEntity) EntityDTOConverter.convertToEntity(stepDTO)).toList();
                }
            }
            return new TaskEntity(taskDTO.id(), taskDTO.title(), taskDTO.description(), stepEntities);
        } else if (baseDTO instanceof StepDTO stepDTO) {
            return new StepEntity(stepDTO.id(), stepDTO.instruction(), null);
        } else {
            return null;
        }
    }

    public static BaseDTO convertToDTO(BaseEntity baseEntity) {
        if (baseEntity instanceof TaskEntity taskEntity) {
            List<StepDTO> stepDTOS;
            if (taskEntity.getSteps() != null && !taskEntity.getSteps().isEmpty()) {
                stepDTOS = taskEntity.getSteps().stream().map(stepEntity -> (StepDTO) EntityDTOConverter.convertToDTO(stepEntity)).toList();
            } else {
                stepDTOS = new ArrayList<>();
            }
            return new TaskDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(), Optional.of(stepDTOS));
        } else if (baseEntity instanceof StepEntity stepEntity) {
            return new StepDTO(stepEntity.getId(), stepEntity.getInstruction());
        } else {
            return null;
        }
    }
}

