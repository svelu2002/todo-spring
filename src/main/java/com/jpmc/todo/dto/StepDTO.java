package com.jpmc.todo.dto;

import com.jpmc.todo.model.TaskEntity;

public record StepDTO(int id, int ordinalNumber, String instruction) implements BaseDTO {
}
