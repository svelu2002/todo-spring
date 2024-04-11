package com.jpmc.todo.util;

import com.jpmc.todo.dto.BaseDTO;
import com.jpmc.todo.dto.StepDTO;
import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.model.BaseEntity;
import com.jpmc.todo.model.StepEntity;
import com.jpmc.todo.model.TaskEntity;

import java.util.List;


public class EntityDTOConverter {
    public static BaseEntity convertToEntity(BaseDTO baseDTO) {
        if (baseDTO instanceof TaskDTO taskDTO) {
            List<StepEntity> stepEntities = null;
            if (!taskDTO.steps().isEmpty()) {
                stepEntities = taskDTO.steps().stream().map(stepDTO -> (StepEntity) EntityDTOConverter.convertToEntity(stepDTO)).toList();
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
            List<StepDTO> stepDTOS = null;
            if (!taskEntity.getSteps().isEmpty()) {
                stepDTOS = taskEntity.getSteps().stream().map(stepEntity -> (StepDTO) EntityDTOConverter.convertToDTO(stepEntity)).toList();
            }
            return new TaskDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(), stepDTOS);
        } else if (baseEntity instanceof StepEntity stepEntity) {
            return new StepDTO(stepEntity.getId(), stepEntity.getInstruction());
        } else {
            return null;
        }
    }
}

