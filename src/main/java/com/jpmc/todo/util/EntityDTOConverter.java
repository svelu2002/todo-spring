package com.jpmc.todo.util;

import com.jpmc.todo.dto.BaseDTO;
import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.model.BaseEntity;
import com.jpmc.todo.model.TaskEntity;


public class EntityDTOConverter {
    public static BaseEntity convertToEntity(BaseDTO baseDTO) {
        if (baseDTO instanceof TaskDTO taskDTO) {
            return new TaskEntity(taskDTO.id(), taskDTO.title(), taskDTO.description());
        } else {
            return null;
        }
    }

    public static BaseDTO convertToDTO(BaseEntity baseEntity) {
        if (baseEntity instanceof TaskEntity taskEntity) {
            return new TaskDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription());
        } else {
            return null;
        }
    }
}

