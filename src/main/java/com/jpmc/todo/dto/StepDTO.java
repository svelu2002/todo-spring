package com.jpmc.todo.dto;

import com.jpmc.todo.model.TaskEntity;

public record StepDTO(int id, String instruction) implements BaseDTO {
}
