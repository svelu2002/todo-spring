package com.jpmc.todo.util;

import com.jpmc.todo.dto.*;
import com.jpmc.todo.model.*;

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
            return new TaskEntity(taskDTO.id(), taskDTO.title(), taskDTO.description(), taskDTO.status(), stepEntities);
        } else if (baseDTO instanceof StepDTO stepDTO) {
            return new StepEntity(stepDTO.id(), stepDTO.ordinalNumber(), stepDTO.instruction(), null);
        } else if (baseDTO instanceof UserDTO userDTO) {
            return new UserEntity(userDTO.id(), userDTO.username(), userDTO.password(), userDTO.role(), null);
        } else if (baseDTO instanceof UserProfileDTO userProfileDTO) {
            return new UserProfileEntity(userProfileDTO.id(), userProfileDTO.fullName(), userProfileDTO.gender(), userProfileDTO.dob(), null);
        } else if (baseDTO instanceof ProductDTO productDTO) {
            return new ProductEntity(productDTO.id(), productDTO.name(), productDTO.price(), null);
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
            return new TaskDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getStatus(), Optional.of(stepDTOS));
        } else if (baseEntity instanceof StepEntity stepEntity) {
            return new StepDTO(stepEntity.getId(), stepEntity.getOrdinalNumber(), stepEntity.getInstruction());
        } else if (baseEntity instanceof UserEntity userEntity) {
            return new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole(), userEntity.getProfile().getFullName());
        } else if (baseEntity instanceof UserProfileEntity userProfileEntity) {
            return new UserProfileDTO(userProfileEntity.getId(), userProfileEntity.getFullName(), userProfileEntity.getGender(), userProfileEntity.getDob());
        } else if (baseEntity instanceof ProductEntity productEntity) {
            return new ProductDTO(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
        } else {
            return null;
        }
    }
}

